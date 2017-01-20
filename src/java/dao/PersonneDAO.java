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
public interface PersonneDAO {
    public void save(PersonneEntity personne);
    public void update(PersonneEntity personne);
    public void delete(PersonneEntity personne);
    public PersonneEntity find(long id);
    public List<PersonneEntity> findAll();
    public List<PersonneEntity> findByName(String nom);
    public List<PersonneEntity> findByLogin(String login);
}
