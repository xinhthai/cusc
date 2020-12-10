package com.myxinh.cusc.service;

import com.myxinh.cusc.domain.Category;
import com.myxinh.cusc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> updateCategory(Category category){
        return Optional.of(categoryRepository
            .findById(category.getCategoryId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(oldCategory -> new Category(category.getCategoryId(),category.getCategoryName()));
    }

    public void deleteCategory(int categoryId){
        categoryRepository.findById(categoryId).ifPresent(
                category -> {categoryRepository.delete(category);}
        );
    }
}
