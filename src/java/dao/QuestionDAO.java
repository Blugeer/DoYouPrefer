/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author natha_000
 */
public interface QuestionDAO {
    public void save(QuestionEntity question);
    public void update(QuestionEntity question);
    public void delete(QuestionEntity question);
    public QuestionEntity find(long id);
    public List<QuestionEntity> findAll();
}
