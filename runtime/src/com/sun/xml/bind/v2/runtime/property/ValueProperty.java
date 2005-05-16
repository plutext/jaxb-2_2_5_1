package com.sun.xml.bind.v2.runtime.property;


import java.io.IOException;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.stream.XMLStreamException;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.QNameMap;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeValuePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;

import org.xml.sax.SAXException;

/**
 * {@link Property} implementation for {@link XmlValue} properties.
 *
 * <p>
 * This one works for both leaves and nodes, scalars and arrays.
 *
 * @author Bhakti Mehta (bhakti.mehta@sun.com)
 */
final class ValueProperty<BeanT,ListT,ItemT> extends PropertyImpl<BeanT> {

    /**
     * Heart of the conversion logic.
     */
    private final TransducedAccessor<BeanT> xacc;
    private final Accessor acc;


    public ValueProperty(JAXBContextImpl grammar, RuntimeValuePropertyInfo prop) {
        super(grammar,prop);
        xacc = TransducedAccessor.get(prop);
        acc = prop.getAccessor();   // we only use this for binder, so don't waste memory by optimizing
    }

    public final void serializeBody(BeanT o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
        if(xacc.hasValue(o)) {
            w.text(xacc.print(o),fieldName);
        }
    }

    public Unmarshaller.Handler createUnmarshallerHandler(JAXBContextImpl grammar, Unmarshaller.Handler tail){
         return new Unmarshaller.TextHandler(xacc,Unmarshaller.ERROR, tail);
    }

    public void buildChildElementUnmarshallers(UnmarshallerChain chainElem, QNameMap<Unmarshaller.Handler> handlers) {
    }

    public PropertyKind getKind() {
        return PropertyKind.VALUE;
    }

    public void reset(BeanT o) throws AccessorException {
        acc.set(o,null);
    }

    public String getIdValue(BeanT bean) throws AccessorException, SAXException {
        return xacc.print(bean).toString();
    }

}
