package cz.muni.fi.pa165.blablacar.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface BeanMappingService {

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public <T> T mapTo(Object u, Class<T> mapToClass);

    public Mapper getMapper();

}
