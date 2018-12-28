/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.NewsObject;

/**
 *
 * @author ollik
 */
public interface NewsObjectRepository extends JpaRepository<NewsObject, Long>{
    List<NewsObject> findAllByCategory(String category);
}
