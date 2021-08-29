package SimiAlex.com.github.applicationB.dao;

import SimiAlex.com.github.applicationB.dto.MessageDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class MessageRepository {

    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public void addMessage(MessageDTO messageDTO){
        em.persist(messageDTO);
    }

    public List<MessageDTO> findAllMessages(){
        List<MessageDTO> messages = em.createQuery("from MessageDTO", MessageDTO.class).getResultList();
        return messages;
    }
}
