package com.csiszer.my_shop.service.category;

import com.csiszer.my_shop.exceptions.AlreadyExistsExcepptions;
import com.csiszer.my_shop.exceptions.ResourceNotFoundException;
import com.csiszer.my_shop.model.Category;
import com.csiszer.my_shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).
                orElseThrow( () -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.finByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCaegory(Category category) {
        return Optional.ofNullable(category).filter(cat -> !categoryRepository.existsByName(cat.getName())).
                map(categoryRepository :: save).
                orElseThrow( () -> new AlreadyExistsExcepptions(category.getName() + "already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).
                map( oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow( () -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategoryByid(Long id) {
        categoryRepository.findById(id).
                ifPresentOrElse(categoryRepository :: delete,
                        () -> new ResourceNotFoundException(("Category not found")));

    }
}
