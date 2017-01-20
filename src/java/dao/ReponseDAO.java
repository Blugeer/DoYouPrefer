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
public interface ReponseDAO {
    public void save(ReponseEntity reponse);
    public void update(ReponseEntity reponse);
    public void delete(ReponseEntity reponse);
    public ReponseEntity find(long id);
    public List<ReponseEntity> findAll();
}
