package com.sun.xml.bind.v2.runtime.property;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLStreamException;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;

import org.xml.sax.SAXException;

/**
 * A JAXB property that constitutes a JAXB-bound bean.
 *
 * @author Kohsuke Kawaguchi (kohsuke.kawaguchi@sun.com)
 */
public interface Property<BeanT> extends ChildElementUnmarshallerBuilder {

//    // is this method necessary? --> probably not
//    RuntimePropertyInfo owner();

    /**
     * Resets the property value on the given object.
     *
     * <p>
     * ... for example by setting 0 or null.
     */
    void reset( BeanT o ) throws AccessorException;

    /**
     * @see JaxBeanInfo#serializeBody(Object, XMLSerializer)
     *
     * @param outerPeer
     *      used when this property is expected to print out an element
     *      and that should be associated with this outer peer. normally null.
     *      this is only used for {@link JaxBeanInfo} for {@link JAXBElement}s.
     * @throws AccessorException
     *      If thrown, caught by the caller and reported.
     */
    public void serializeBody(BeanT beanT, XMLSerializer target, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException;

    /**
     * @see JaxBeanInfo#serializeURIs(Object, XMLSerializer)
     */
    public void serializeURIs(BeanT beanT, XMLSerializer target) throws SAXException, AccessorException;

//    /**
//     * Builds the unmarshaller.
//     *
//     * @param grammar
//     *      the context object to which this property ultimately belongs to.
//     *      a property will only belong to one grammar, but to reduce the memory footprint
//     *      we don't keep that information stored in {@link Property}, and instead we
//     *      just pass the value as a parameter when needed.
//     */
//    Unmarshaller.Handler createUnmarshallerHandler(JAXBContextImpl grammar, Unmarshaller.Handler tail);

    /**
     * Gets the value of the property.
     *
     * This method is only used when the corresponding {@link PropertyInfo#id()} is {@link ID#ID},
     * and therefore the return type is fixed to {@link String}.
     */
    String getIdValue(BeanT bean) throws AccessorException, SAXException;

    /**
     * Gets the Kind of property
     * @return
     *      always non-null.
     */
    PropertyKind getKind();


    // UGLY HACK to support JAX-RPC
    // if other clients want to access those functionalities,
    // we should design a better model
    /**
     * If this property is mapped to the specified element,
     * return an accessor to it.
     *
     * @return
     *      null if the property is not mapped to the specified element.
     */
    Accessor getElementPropertyAccessor(String nsUri,String localName);

    /**
     * Called at the end of the {@link JAXBContext} initialization phase
     * to clean up any unnecessary references.
     */
    void wrapUp();
}
