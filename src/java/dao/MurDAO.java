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
public interface MurDAO {
    public void save(MurEntity mur);
    public void update(MurEntity mur);
    public void delete(MurEntity mur);
    public MurEntity find(long id);
    public List<MurEntity> findAll();
}
