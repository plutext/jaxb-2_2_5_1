/**
 * <h1>JAXB RI Architecture Document</h1>.
 *
 * <h2>JAXB RI Major Modules and Libraries</h2>
 * <div>
 *   <img src="doc-files/jaxbModulesLibs.png"/>
 * </div>
 *
 *
 * <h2>Modules</h2>
 * <p>
 * The JAXB RI consists of the following major modules.
 *
 * <dl>
 *  <dt>{@link com.sun.xml.bind.v2 runtime}
 *  <dd>
 *    runtime module is available at application runtime and provide the actual
 *    XML unmarshalling/marshalling capability. Notably, it implements {@link JAXBContext}.
 *
 *  <dt>{@link com.sun.tools.xjc XJC}
 *  <dd>
 *    The schema compiler.
 *
 *  <dt>{@link com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator schemagen}
 *  <dd>
 *    The XML Schema generator. For historical reason it lives in its own module.
 *
 *  <dt>{@link com.sun.xml.bind.api runtime-API}</a>
 *  <dd>
 *    A part of the runtime that defines the contract between the JAXB RI and
 *    the JAX-WS RI.
 *
 * </dl>
 *
 * <h2>Libraries</h2>
 * <p>
 * JAXB RI uses the following major libraries extensively.
 * The parenthesis shows which modules of the JAXB RI depends on it.
 * <dl>
 *  <dt><a href="../com/sun/codemodel/package-summary.html#package_description">CodeModel</a> (XJC)
 *  <dd>
 *    Library for generating Java source code
 *
 *  <dt><a href="#">XSOM</a> (XJC)
 *  <dd>
 *    Library for parsing XML Schema into in-memory representations
 *
 *  <dt><a href="http://rngom.dev.java.net/">RNGOM</a> (XJC)
 *  <dd>
 *    Library for parsing RELAX NG into in-memory representation
 *
 *  <dt><a href="#">TXW</a> (runtime,schemagen)
 *  <dd>
 *    Library for writing XML
 * </dl>
 *
 *
 * @ArchitectureDocument
 */
package jaxb;

import javax.xml.bind.JAXBContext;
