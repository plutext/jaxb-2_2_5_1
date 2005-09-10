/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://jwsdp.dev.java.net/CDDLv1.0.html
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * https://jwsdp.dev.java.net/CDDLv1.0.html  If applicable,
 * add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your
 * own identifying information: Portions Copyright [yyyy]
 * [name of copyright owner]
 */
package com.sun.tools.xjc.api;

import java.util.List;

import javax.xml.namespace.QName;

/**
 * JAXB-induced mapping between a Java class
 * and an XML element declaration. A part of the compiler artifacts.
 *
 * <p>
 * To be precise, this is a mapping between two Java classes and an
 * XML element declaration. There's one Java class/interface that
 * represents the element, and there's another Java class/interface that
 * represents the type of the element.
 *
 * The former is called "element representation" and the latter is called
 * "type representation".
 *
 * <p>
 * The {@link Mapping} interface provides operation that lets the caller
 * convert an instance of the element representation to that of the
 * type representation or vice versa.
 *
 * @author
 *     Kohsuke Kawaguchi (kohsuke.kawaguchi@sun.com)
 */
public interface Mapping {
    /**
     * Name of the XML element.
     *
     * @return
     *      never be null.
     */
    QName getElement();

    /**
     * Returns the fully-qualified name of the java class for the type of this element.
     *
     * TODO: does this method returns the name of the wrapper bean when it's qualified
     * for the wrapper style? Seems no (consider &lt;xs:element name='foo' type='xs:long' />),
     * but then how does JAX-RPC captures that bean?
     *
     * @return
     *      never be null.
     */
    TypeAndAnnotation getType();

    /**
     * If this element is a so-called "wrapper-style" element,
     * obtains its member information.
     *
     * <p>
     * The notion of the wrapper style should be defined by the JAXB spec,
     * and ideally it should differ from that of the JAX-RPC only at
     * the point where the JAX-RPC imposes additional restriction
     * on the element name.
     *
     * <p>
     * As of this writing the JAXB spec doesn't define "the wrapper style"
     * and as such the exact definition of what XJC thinks
     * "the wrapper style" isn't spec-ed.
     *
     * @return
     *      null if this isn't a wrapper-style element.
     *      Otherwise list of {@link Property}s. The order signifies
     *      the order they appeared inside a schema.
     */
    List<? extends Property> getWrapperStyleDrilldown();
}