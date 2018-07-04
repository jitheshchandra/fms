package com.ssrv.fms.util.db.idgenerators;

import java.io.Serializable;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class DictationGenerator implements IdentifierGenerator{
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(DictationGenerator.class);
	
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		
		final long MAX_NUMBER_YOU_WANT_TO_HAVE = 9999999999L;
		final long MIN_NUMBER_YOU_WANT_TO_HAVE = 1000000000L;
		Long randNo = Long.valueOf(new Random().nextLong() * 
		             (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE));
		return randNo.toString();
	}

}
