package com.sample.rest.demo.springbootrest.repositories;

import com.mongodb.WriteResult;
import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private Datastore datastore;

    @Override
    public String saveOrUpdateContact(Contact contact) {
        if(null!= contact && contact.validate())
            return extractObjectId(datastore.save(contact));

        return null;
    }

    @Override
    public Contact getContactById(ObjectId id){
        if(null!=id)
            return datastore.createQuery(Contact.class).filter("_id", id).get();

        return null;
    }

    @Override
    public Contact getContact(String firstName, String lastName) {
        if(null!=firstName && null!=lastName){
            Query<Contact> query = datastore.createQuery(Contact.class);
            query.and(
                query.criteria("details.firstName").equal(firstName),
                query.criteria("details.lastName").equal(lastName)
            );
            return query.get();
        }
        return null;
    }

    @Override
    public String updateContact(Contact contact) {
        if(null!= contact && contact.validate()) {
            Contact dbContact = getContactById(contact.getId());
            if(null!=dbContact){
                if(null!=contact.getDetails() && contact.getDetails().validate())
                    dbContact.setDetails(contact.getDetails());

                if(null!=contact.getContactNumbers() && contact.getContactNumbers().validate())
                    dbContact.setContactNumbers(contact.getContactNumbers());

                dbContact.setLastModifiedDate(new Date());

                return extractObjectId(datastore.save(dbContact));
            }
        }

        return null;
    }

    @Override
    public boolean deleteContact(Contact contact) {
        if(null!= contact && contact.validate()) {
            Contact dbContact = getContactById(contact.getId());
            if(null!=dbContact) return null != datastore.delete(dbContact);
        }

        return false;
    }

    @Override
    public List<Contact> listContact(String owner) {
        Query<Contact> query = datastore.createQuery(Contact.class);
        if(null!=owner && 0 < owner.trim().length()) query.filter("owner", owner);
        query.order("details.firstName, details.lastName");
        return query.asList();
    }

    private String extractObjectId(Key<Contact> keyContact){
        if(null!=keyContact)
            return keyContact.getId().toString();
        else
            return null;
    }
}
