package nl.gridshore.rdm.context;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import nl.gridshore.rdm.utils.DomainContextFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;

class DomainContextProxyFactoryBean implements FactoryBean {
    private final SpringConfigurableDomainContextFactory factory;
    private final Class domainContextType;
    private final Enhancer enhancer;

    public DomainContextProxyFactoryBean(final SpringConfigurableDomainContextFactory factory, final Class domainContextType) {
        this.factory = factory;
        this.domainContextType = domainContextType;
        this.enhancer = new Enhancer();
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(final Object o, final Method method, final Object[] objects) throws Throwable {
                if (factory.getCurrentContext() == null) {
                    throw new NoContextAvailableException(String.format("No context of type %s available.", o.getClass().getSimpleName()));
                }
                return method.invoke(factory.getCurrentContext(), objects);
            }
        });
        enhancer.setSuperclass(domainContextType);
    }

    @Override
    public Object getObject() throws Exception {
        return enhancer.create(new Class[]{DomainContextFactory.class}, new Object[]{factory});
    }

    @Override
    public Class getObjectType() {
        return domainContextType;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}