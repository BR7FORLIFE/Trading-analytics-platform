# este archivo permite scrappear la pagina web y obtener informacion del DOM
# pero no es recomendable usarlo por el simple hecho que hay un factor llamado Cloudflare
# donde bloquea y aveces pide catpchas para verificacion humana, la solucino pues
# usar la API de investing y con la session pues obtener los datos para cada una de las urls

from constants.urls import ECONOMIC_TRADING_INDICATORS_URLS
from playwright.sync_api import sync_playwright, BrowserContext
import subprocess
import os 
from dotenv import load_dotenv
import time
import random

load_dotenv() # cargamos las variables de entorno

# importamos las variables de entorno | Si falla generara una excepcion
INVESTING_EMAIL = os.environ["email"]
INVESTING_PASSWORD = os.environ["password"]
CHROMIUM_CDP_PATH = os.environ["CHROMIUM_CDP_PATH"] # protocolo de chromium devTools protocol 


INVESTING_URL = "https://www.investing.com/"
AUTH_JSON_PATH_CONTEXT = "auth.json"

# informacion que enviaremos a la api de Spring
data: list[dict[str, str]] = []

# Estructura esperada

# data = {
#     "nombre_url": {"name": "VIX", 
#                    "priceLast": "17.03",
#                    "priceChange": "+0.006", 
#                    "priceChangePercent": "+3.04%"}
# }

def saveContext(ctx: BrowserContext):
    ctx.storage_state(path=AUTH_JSON_PATH_CONTEXT) 


# IMPORTANTE! -> SE DEBE INICIAR SESSION EN EL LOGIN POR DEFECTO DE INVESTING
# YA QUE GOOGLE DETECTA QUE EL NAVEGADOR SE HA ABIERTO DE FORMA AUTOMATIZADA Y NO MANUAL

# esta funcion nos va a permitir autenticarnos dentro de investing y asi guardar el contexto
# para empezar a scrapear entre paginas
def authenticate(): 
    # abrimos el navegador en modo debugging para que el protocolo CDP funcione en playwrigt
    subprocess.Popen(["brave-browser", 
                      "--remote-debugging-port=9222", 
                      "--user-data-dir=/tmp/investing-brave",
                        INVESTING_URL])

    # abrimos investing en la pagina principal y el usuario debe iniciar session manualmente

    print("Inicia session manualmente y despues preciona enter para continuar con el scrapper")
    input("presiona enter .....")

    with sync_playwright() as contextManager: 
        browser = contextManager.chromium.connect_over_cdp(CHROMIUM_CDP_PATH) # nos conectamos por CDP

        # guardamos el contexto de investing (Por eso se abre una sola pestaña de navegador)
        context = browser.contexts[0] 
        investing_page = None

        for page in context.pages:
            if "investing.com" in page.url:
                investing_page = page
                break

        if investing_page is None:
            raise RuntimeError(
                "No se encontro pestaña de investing abierta"
            )  

        saveContext(ctx=context) # guardamos el contexto
        browser.close()
            

def scrapper(): 
    with sync_playwright() as contextManager: 
        # abrimos el navegador ya que asi chrome nos permite obtener la informacion ya que es un
        # navegador con interfaz grafica
        browser = contextManager.chromium.launch(headless=False) 

        # cargamos el contexto que en este caso es nuestra session de auth de investing
        context = browser.new_context(storage_state=AUTH_JSON_PATH_CONTEXT)

        page = context.new_page() # creamos una nueva pestaña a la cual navegaremos a los distintos urls

        for url in ECONOMIC_TRADING_INDICATORS_URLS: 

            page.goto(url, wait_until='domcontentloaded', timeout=60000)

            # titulo del indicador
            # devuelve un objeto pero como queremos su valor pues lo obtenemos con otro metodo
            name = page.locator("h1").inner_text()

            # puntos bases actuales
            priceLast = page.locator("[data-test='instrument-price-last']").inner_text()

            # que tanto cambio con respecto al cierre anterior
            priceChange = page.locator("[data-test='instrument-price-change']").inner_text()

            priceChangePercent = page.locator("[data-test='instrument-price-change-percent']").inner_text()

            data.append({
                "name": name,
                "priceLast": priceLast,
                "priceChange": priceChange,
                "priceChangePercent": priceChangePercent
            }) 

            time.sleep(random.uniform(3,7))

        browser.close() # cerrar el navegador
