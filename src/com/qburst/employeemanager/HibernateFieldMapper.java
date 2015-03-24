package com.qburst.employeemanager;

import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

public class HibernateFieldMapper implements CustomFieldMapper
{

    // else if ( sourceFieldValue. )
    // {
    // // Allow dozer to map as normal
    // return false;
    // }
    @Override
    public boolean mapField( Object source, Object destination, Object sourceFieldValue,
        ClassMap classMap, FieldMap fieldMapping )
    {
        if ( !( sourceFieldValue instanceof PersistentCollection ) && !( sourceFieldValue instanceof
            HibernateProxy ) )
        {
            return false;
        }
        else if ( sourceFieldValue instanceof PersistentSet
            && ( ( PersistentSet ) sourceFieldValue ).wasInitialized() )
        {
            return false;
        }

        else
        {
            if ( Hibernate.isInitialized( sourceFieldValue ) )
            {
                return false;
            }
            else
            {
                destination = null;
                return true;
            }
        }
        // Check if field is a Hibernate PersistentSet
        // if ( !( sourceFieldValue instanceof PersistentSet ) )
        // {
        // // Allow dozer to map as normal
        // return false;
        // }

        // Check if field is already initialized

        // Set destination to null, and tell dozer that the field is mapped

    }
}
