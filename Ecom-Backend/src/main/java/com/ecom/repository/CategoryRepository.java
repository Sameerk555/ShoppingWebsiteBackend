package com.ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {

}
