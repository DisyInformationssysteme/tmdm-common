// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.2_01, construire R40)
// Generated source version: 1.1.2

package com.amalto.webapp.util.webservices;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.xsd.XSDConstants;
import com.sun.xml.rpc.encoding.literal.*;
import com.sun.xml.rpc.encoding.literal.DetailFragmentDeserializer;
import com.sun.xml.rpc.encoding.simpletype.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.ArrayList;

public class WSPutItem_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable  {
    private static final QName ns1_wsDataClusterPK_QNAME = new QName("", "wsDataClusterPK");
    private static final QName ns2_WSDataClusterPK_TYPE_QNAME = new QName("urn-com-amalto-xtentis-webservice", "WSDataClusterPK");
    private CombinedSerializer ns2_myWSDataClusterPK_LiteralSerializer;
    private static final QName ns1_xmlString_QNAME = new QName("", "xmlString");
    private static final QName ns3_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns3_myns3_string__java_lang_String_String_Serializer;
    private static final QName ns1_wsDataModelPK_QNAME = new QName("", "wsDataModelPK");
    private static final QName ns2_WSDataModelPK_TYPE_QNAME = new QName("urn-com-amalto-xtentis-webservice", "WSDataModelPK");
    private CombinedSerializer ns2_myWSDataModelPK_LiteralSerializer;
    
    public WSPutItem_LiteralSerializer(QName type, String encodingStyle) {
        this(type, encodingStyle, false);
    }
    
    public WSPutItem_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
        super(type, true, encodingStyle, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        ns2_myWSDataClusterPK_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.amalto.webapp.util.webservices.WSDataClusterPK.class, ns2_WSDataClusterPK_TYPE_QNAME);
        ns3_myns3_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns3_string_TYPE_QNAME);
        ns2_myWSDataModelPK_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.amalto.webapp.util.webservices.WSDataModelPK.class, ns2_WSDataModelPK_TYPE_QNAME);
    }
    
    public Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        com.amalto.webapp.util.webservices.WSPutItem instance = new com.amalto.webapp.util.webservices.WSPutItem();
        Object member=null;
        QName elementName;
        List values;
        Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_wsDataClusterPK_QNAME)) {
                member = ns2_myWSDataClusterPK_LiteralSerializer.deserialize(ns1_wsDataClusterPK_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setWsDataClusterPK((com.amalto.webapp.util.webservices.WSDataClusterPK)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_wsDataClusterPK_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_xmlString_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_xmlString_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setXmlString((java.lang.String)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_xmlString_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_wsDataModelPK_QNAME)) {
                member = ns2_myWSDataModelPK_LiteralSerializer.deserialize(ns1_wsDataModelPK_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setWsDataModelPK((com.amalto.webapp.util.webservices.WSDataModelPK)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_wsDataModelPK_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (Object)instance;
    }
    
    public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.webapp.util.webservices.WSPutItem instance = (com.amalto.webapp.util.webservices.WSPutItem)obj;
        
    }
    public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.webapp.util.webservices.WSPutItem instance = (com.amalto.webapp.util.webservices.WSPutItem)obj;
        
        if (instance.getWsDataClusterPK() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myWSDataClusterPK_LiteralSerializer.serialize(instance.getWsDataClusterPK(), ns1_wsDataClusterPK_QNAME, null, writer, context);
        if (instance.getXmlString() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getXmlString(), ns1_xmlString_QNAME, null, writer, context);
        if (instance.getWsDataModelPK() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myWSDataModelPK_LiteralSerializer.serialize(instance.getWsDataModelPK(), ns1_wsDataModelPK_QNAME, null, writer, context);
    }
}
