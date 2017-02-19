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
public interface NotificationDAO {
    public void save(NotificationEntity notif);
    public void update(NotificationEntity notif);
    public void delete(NotificationEntity notif);
    public void deleteAll(String login);
    public NotificationEntity find(long id);
    public List<NotificationEntity> findByLogin(String login);
    public List<NotificationEntity> findAll();
}
