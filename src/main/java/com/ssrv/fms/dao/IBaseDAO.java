package com.ssrv.fms.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO {
	<T> T findById(Class<T> resultClass, Serializable primaryKey);

	<T> List<T> getAll(Class<T> resultClass);

	<T> T save(T t);

	<T> void delete(T t);
}
