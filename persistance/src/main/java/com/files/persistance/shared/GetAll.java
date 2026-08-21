package com.files.persistance.shared;

import java.util.List;


public record GetAll<T>(List<T> data, PaginationHelper pagination) {

}
