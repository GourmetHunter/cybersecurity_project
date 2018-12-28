/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.repository;

import java.util.List;
import sec.project.domain.NewsObject;
import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Hits;

/**
 *
 * @author ollik
 */
public interface HitsRepository extends JpaRepository<Hits, Long>{
    List<Hits> findAllByNewsObject(NewsObject newsObject);
}
