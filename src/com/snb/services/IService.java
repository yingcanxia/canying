package com.snb.services;

import java.util.Map;

public interface IService {
	public Map query(Map map) throws Exception;
	public Map save(Map map) throws Exception;
	public Map update(Map map) throws Exception;
	public Map delete(Map map) throws Exception;
}
