# 📊 Plataforma de Datos Económicos y Trading

## 📌 Descripción

Proyecto personal enfocado en la **recopilación, persistencia, procesamiento y visualización de datos económicos y financieros relacionados con los mercados de trading**.

La plataforma permitirá obtener información desde diferentes fuentes, almacenarla de forma estructurada y representarla mediante un dashboard web para facilitar su consulta y análisis.

El proyecto busca construir una solución sencilla y funcional, priorizando la **implementación de la idea y el aprendizaje práctico** sobre la complejidad arquitectónica.

---

# 🎯 Objetivos

* Recopilar datos económicos y financieros desde diferentes fuentes.
* Persistir la información en una base de datos PostgreSQL.
* Exponer los datos mediante una API.
* Construir un dashboard para visualizar la información.
* Automatizar la obtención y actualización de los datos cuando sea necesario.
* Desplegar la aplicación utilizando servicios de AWS.
* Mantener una arquitectura sencilla, clara y fácil de mantener.

---

# 🏗️ Estructura del proyecto

El proyecto estará dividido en cuatro áreas principales:

```text
Proyecto
│
├── Backend
│
├── Frontend
│
├── Persistencia de información
│
└── Despliegue
```

---

# ⚙️ Backend

El backend será responsable de proporcionar la API que permitirá consultar y gestionar la información almacenada.

### Tecnologías

* **Java**
* **Spring Boot**
* **Spring WebFlux**
* **Arquitectura basada en capas**
* **PostgreSQL**

### Responsabilidades

* Exponer endpoints REST.
* Consultar información almacenada.
* Gestionar las operaciones relacionadas con los datos.
* Validar las solicitudes recibidas.
* Gestionar errores.
* Conectar con la capa de persistencia.
* Proporcionar los datos necesarios para el frontend.

### Arquitectura

Se utilizará una arquitectura basada en capas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

La intención es mantener una separación clara de responsabilidades sin introducir patrones o arquitecturas innecesariamente complejas.

---

# 🗄️ Persistencia de la información

La información será almacenada utilizando **PostgreSQL**.

La base de datos será responsable de almacenar tanto información económica como información relacionada con los mercados financieros.

### Posibles datos

* Indicadores económicos.
* Precios de activos.
* Datos históricos.
* Tasas de interés.
* Rendimientos de bonos.
* Información relacionada con eventos económicos.
* Fechas de publicación.
* Valores actuales, anteriores y esperados.
* Información obtenida mediante scraping.

### Requerimientos

* Diseñar un modelo de datos adecuado para la información recopilada.
* Mantener relaciones entre las diferentes entidades.
* Registrar fechas y horas de los datos.
* Evitar duplicidad de información.
* Permitir consultas históricas.
* Mantener la integridad de los datos.

---

# 🕷️ Obtención y scraping de datos

La plataforma necesitará obtener información desde fuentes externas.

Dependiendo de la fuente, se utilizarán APIs disponibles o técnicas de scraping.

### Tecnologías consideradas

* **Playwright**
* Otras herramientas de scraping cuando sea necesario.

La implementación deberá mantenerse sencilla.

No se busca construir inicialmente un sistema avanzado de scraping, sino disponer de mecanismos suficientes para obtener los datos necesarios para el proyecto.

### Flujo general

```text
Fuente externa
      ↓
Obtención de datos
      ↓
Procesamiento / normalización
      ↓
Validación
      ↓
PostgreSQL
```

---

# 🖥️ Frontend

El frontend será responsable de representar la información económica y financiera mediante una interfaz web.

### Tecnología

* **Astro**

### Responsabilidades

* Mostrar información económica.
* Mostrar información de mercado.
* Representar datos mediante gráficos.
* Permitir consultar información histórica.
* Mostrar eventos económicos.
* Facilitar la navegación entre diferentes tipos de información.

### Dashboard

El dashboard podrá incluir diferentes secciones, por ejemplo:

```text
Dashboard
│
├── Market Overview
│
├── Economic Data
│
├── Economic Calendar
│
├── Interest Rates
│
├── Market Prices
│
└── Historical Data
```

La estructura definitiva del dashboard se definirá durante el desarrollo.

---

# ☁️ Despliegue

El proyecto será desplegado utilizando **Amazon Web Services (AWS)**.

El objetivo es desplegar una infraestructura sencilla que permita ejecutar la aplicación sin introducir complejidad innecesaria.

### Objetivos del despliegue

* Desplegar el backend.
* Desplegar el frontend.
* Proporcionar acceso a la base de datos.
* Configurar la comunicación entre los diferentes componentes.
* Gestionar variables de entorno y secretos.
* Permitir actualizar la aplicación de forma sencilla.

La infraestructura podrá evolucionar conforme las necesidades del proyecto.

---

# 🔄 Flujo general del sistema

```text
                 FUENTES EXTERNAS
                       │
                       ▼
              APIs / Web Scraping
                       │
                       ▼
              Procesamiento de datos
                       │
                       ▼
                 PostgreSQL
                       │
                       ▼
                 Spring WebFlux
                       │
                       ▼
                    REST API
                       │
                       ▼
                     Astro
                       │
                       ▼
                  DASHBOARD
```

---

# 📋 Requerimientos funcionales

## Datos

* [ ] Obtener datos económicos desde fuentes externas.
* [ ] Obtener datos financieros relacionados con los mercados.
* [ ] Procesar y normalizar los datos obtenidos.
* [ ] Almacenar los datos en PostgreSQL.
* [ ] Evitar registros duplicados.
* [ ] Mantener información histórica.

## Backend

* [ ] Crear el proyecto utilizando Spring Boot.
* [ ] Utilizar Spring WebFlux.
* [ ] Implementar arquitectura basada en capas.
* [ ] Crear endpoints REST.
* [ ] Implementar acceso a PostgreSQL.
* [ ] Implementar validación de solicitudes.
* [ ] Implementar manejo de errores.
* [ ] Exponer los datos necesarios para el frontend.

## Frontend

* [ ] Crear la aplicación utilizando Astro.
* [ ] Crear el dashboard principal.
* [ ] Mostrar información económica.
* [ ] Mostrar información financiera.
* [ ] Crear visualizaciones mediante gráficos.
* [ ] Permitir consultar información histórica.
* [ ] Crear una interfaz clara y sencilla.

## Scraping / Ingesta

* [ ] Identificar las fuentes de información.
* [ ] Implementar clientes para APIs cuando estén disponibles.
* [ ] Implementar scraping cuando sea necesario.
* [ ] Utilizar Playwright u otra herramienta adecuada.
* [ ] Normalizar los datos obtenidos.
* [ ] Persistir automáticamente la información.

## Despliegue

* [ ] Crear la infraestructura necesaria en AWS.
* [ ] Desplegar el backend.
* [ ] Desplegar el frontend.
* [ ] Configurar PostgreSQL.
* [ ] Configurar variables de entorno.
* [ ] Configurar comunicación entre servicios.
* [ ] Verificar el funcionamiento de la aplicación desplegada.

---

# 🔒 Requerimientos no funcionales

* El sistema debe mantener una arquitectura sencilla.
* El código debe estar organizado y ser mantenible.
* Las responsabilidades de cada componente deben estar claramente separadas.
* Los datos almacenados deben mantener consistencia e integridad.
* La aplicación debe poder ejecutarse tanto localmente como en AWS.
* Las credenciales y secretos no deben almacenarse directamente en el código.
* La solución debe evitar complejidad innecesaria.

---

# 🚧 Alcance

El proyecto se centrará inicialmente en:

```text
Obtención de datos
        ↓
Persistencia
        ↓
API
        ↓
Dashboard
        ↓
Despliegue
```

No se busca inicialmente implementar:

* Sistemas de trading automatizado.
* Ejecución de órdenes.
* Estrategias de trading.
* Machine Learning avanzado.
* Arquitecturas de microservicios.
* Sistemas distribuidos complejos.
* Infraestructura excesivamente sofisticada.

El objetivo principal es **construir una plataforma funcional para trabajar con datos económicos y financieros de trading**.

---

# 🛠️ Stack tecnológico

| Área               | Tecnología                   |
| ------------------ | ---------------------------- |
| Backend            | Java + Spring Boot           |
| Comunicación       | Spring WebFlux               |
| Arquitectura       | Arquitectura basada en capas |
| Base de datos      | PostgreSQL                   |
| Frontend           | Astro                        |
| Obtención de datos | APIs / Playwright / Scraping |
| Cloud              | AWS                          |

---

# 🚀 Resultado esperado

Al finalizar el proyecto se espera disponer de una plataforma web capaz de:

```text
1. Obtener datos económicos y financieros
                ↓
2. Procesarlos y normalizarlos
                ↓
3. Almacenarlos en PostgreSQL
                ↓
4. Exponerlos mediante una API
                ↓
5. Visualizarlos mediante un dashboard
                ↓
6. Ejecutar todo el sistema desplegado en AWS
```

El proyecto será desarrollado de manera incremental, priorizando primero una **versión funcional y sencilla** y agregando nuevas capacidades únicamente cuando aporten valor al sistema.
