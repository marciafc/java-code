package code.jpa.audit.infra;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import code.jpa.audit.model.LogDatabase;
import code.jpa.audit.repository.LogDatabaseRepository;
import io.github.teastman.hibernate.annotation.HibernateEventListener;
import io.github.teastman.hibernate.tool.HibernateEventUtils;

@Service
public class AuditListener {
	@Autowired
	private Javers javers;

	@Autowired
	private LogDatabaseRepository logRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@HibernateEventListener
	public void onSave(Object entity, PreInsertEvent event) {
		System.out.println("Insert:" + entity);
	}
	@Autowired
	private Entities entities;
	
	@HibernateEventListener
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void onUpdate(Object entity, PreUpdateEvent event) {
		try {
			if (entity instanceof Auditable) {
				Auditable auditable = (Auditable) entity;
				String[] names = event.getPersister().getPropertyNames();
				Auditable older = auditable.getClass().newInstance();
				Auditable actual = auditable.getClass().newInstance();
				
				set("id", older, auditable.getId());
				set("id", actual, auditable.getId());
				
				for (String memberName : names) {
					int index = HibernateEventUtils.getPropertyIndex(event, memberName);

					set(memberName, older, event.getOldState()[index]);
					set(memberName, actual, event.getState()[index]);

				}
				compare(older, actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void set(String member, Object objetct, Object value) {
		PropertyAccessor property = PropertyAccessorFactory.forDirectFieldAccess(objetct);
		property.setPropertyValue(member, value);
	}
	private void compare(Auditable older, Auditable actual) {
		Diff diff = javers.compare(older, actual);
		Changes changes = diff.getChanges();
		for (Change c : changes) {
			ValueChange vc = (ValueChange) c;
			LogDatabase log = new LogDatabase();
			log.setCampo(vc.getPropertyName());
			log.setValorAnterior(vc.getLeft().toString());
			log.setValorAtual(vc.getRight().toString());
			// demais campos de dominio
			log.setRegistroId(older.getId().toString());
			log.setTabelaId(entities.getEntidades().get(older.getClass().getSimpleName().toLowerCase()));
			logRepository.save(log);
		}
	}
}