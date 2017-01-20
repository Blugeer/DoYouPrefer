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
public interface MessageDAO {
    public void save(MessageEntity message);
    public void update(MessageEntity message);
    public void delete(MessageEntity message);
    public MessageEntity find(long id);
    public List<MessageEntity> findAll();
}
