package io.santander.gastos.commons;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractMapper<E, D> {

    protected static final MapperFactory factory = createFactory();
    protected static final MapperFacade mapper = factory.getMapperFacade();

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public AbstractMapper() {
        final Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments();
        this.entityClass = (Class<E>) actualTypeArguments[0];
        this.dtoClass = (Class<D>) actualTypeArguments[1];
    }

    public AbstractMapper(final String... fields) {
        this();
        final ClassMapBuilder<E, D> classMap = factory.classMap(entityClass,
                dtoClass);

        for (final String field : fields) {
            final String[] split = field.split("=");
            classMap.field(split[0], split[1]);
        }

        factory.registerClassMap(classMap.byDefault().toClassMap());
    }

    private static MapperFactory createFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    protected D toDTO(final E entity) {
        return mapper.map(entity, dtoClass);
    }

    protected D toDTO(final E entity, final String... exclusions) {
        final MapperFactory instanceFactory = createFactory();
        final ClassMapBuilder<D, E> classMap = instanceFactory.classMap(
                dtoClass, entityClass);
        for (final String exc : exclusions) {
            classMap.exclude(exc);
        }
        classMap.byDefault().register();
        return instanceFactory.getMapperFacade().map(entity, dtoClass);
    }

    protected List<D> toDTOList(final List<E> entity) {
        return mapper.mapAsList(entity, dtoClass);
    }

    protected E toEntity(final D dto) {
        return mapper.map(dto, entityClass);
    }

    protected E toEntity(final D dto, final String... exclusions) {
        final MapperFactory instanceFactory = createFactory();
        final ClassMapBuilder<E, D> classMap = instanceFactory.classMap(
                entityClass, dtoClass);
        for (final String exc : exclusions) {
            classMap.exclude(exc);
        }
        classMap.byDefault().register();
        return instanceFactory.getMapperFacade().map(dto, entityClass);
    }

    protected List<E> toEntityList(final List<D> dto) {
        return mapper.mapAsList(dto, entityClass);
    }

    /**
     * Thread-safe. Create a new MapperFacade for every update
     */
    protected void updateEntity(final E entity, final D dto,
                                final String... exclusions) {
        final MapperFactory instanceFactory = createFactory();
        final ClassMapBuilder<E, D> classMap = instanceFactory.classMap(
                entityClass, dtoClass);
        for (final String exc : exclusions) {
            classMap.exclude(exc);
        }
        classMap.byDefault().register();

        instanceFactory.getMapperFacade(entityClass, dtoClass).mapReverse(dto,
                entity);
    }

}