/*
 * Copyright (C) 2006-2013 Talend Inc. - www.talend.com
 *
 * This source code is available under agreement available at
 * %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
 *
 * You should have received a copy of the agreement
 * along with this program; if not, write to Talend SA
 * 9 rue Pages 92150 Suresnes, France
 */

package org.talend.mdm.commmon.metadata;

import org.talend.mdm.commmon.metadata.validation.ValidationFactory;
import org.talend.mdm.commmon.metadata.validation.ValidationRule;
import org.w3c.dom.Element;

import java.util.*;

/**
 *
 */
public class SoftFieldRef implements FieldMetadata {

    private final MetadataRepository repository;

    private final SoftFieldRef containingField;

    private final TypeMetadata containingType;

    private final String fieldName;

    private final Map<String, Object> additionalData = new HashMap<String, Object>();

    private FieldMetadata frozenField;

    public SoftFieldRef(MetadataRepository metadataRepository, String fieldName, TypeMetadata containingType) {
        this.repository = metadataRepository;
        this.containingType = containingType;
        this.fieldName = fieldName;
        this.containingField = null;
    }

    public SoftFieldRef(MetadataRepository metadataRepository, String fieldName, SoftFieldRef containingField) {
        this.repository = metadataRepository;
        this.containingField = containingField;
        this.containingType = null;
        this.fieldName = fieldName;
    }

    private FieldMetadata getField() {
        return freeze();
    }

    @Override
    public synchronized void setData(String key, Object data) {
        additionalData.put(key, data);
    }

    @Override
    public <X> X getData(String key) {
        return (X) additionalData.get(key);
    }

    @Override
    public String getName() {
        return fieldName;
    }

    @Override
    public boolean isKey() {
        return getField().isKey();
    }

    @Override
    public TypeMetadata getType() {
        return getField().getType();
    }

    @Override
    public ComplexTypeMetadata getContainingType() {
        return (ComplexTypeMetadata) containingType;
    }

    @Override
    public void setContainingType(ComplexTypeMetadata typeMetadata) {
        getField().setContainingType(typeMetadata);
    }

    @Override
    public FieldMetadata freeze() {
        if (frozenField != null) {
            return frozenField;
        }
        ComplexTypeMetadata type = null;
        if (containingType != null) {
            type = repository.getComplexType(containingType.getName());
            if (type == null) {
                TypeMetadata freeze = containingType.freeze();
                frozenField = new UnresolvedFieldMetadata(fieldName,
                        true,
                        (ComplexTypeMetadata) freeze);
            }
        } else {
            FieldMetadata frozenContainingField = containingField.freeze();
            if (frozenContainingField instanceof UnresolvedFieldMetadata) {
                frozenField = new UnresolvedFieldMetadata(fieldName,
                        true,
                        (ComplexTypeMetadata) containingField.getContainingType().freeze());
            } else {
                type = (ComplexTypeMetadata) containingField.getType();
            }
        }
        if (type != null) {
            if (!type.hasField(fieldName)) {
                frozenField = new UnresolvedFieldMetadata(fieldName,
                        true,
                        type);
            } else {
                frozenField = type.getField(fieldName).copy(repository).freeze();
            }
        }
        // Add additional data (line number...).
        Set<Map.Entry<String,Object>> data = additionalData.entrySet();
        for (Map.Entry<String, Object> currentData : data) {
            frozenField.setData(currentData.getKey(), currentData.getValue());
        }
        return frozenField;
    }

    @Override
    public void promoteToKey() {
        getField().promoteToKey();
    }

    @Override
    public void validate(ValidationHandler handler) {
        // Get line and column numbers
        Integer lineNumberObject = (Integer) additionalData.get(MetadataRepository.XSD_LINE_NUMBER);
        Integer columnNumberObject = (Integer) additionalData.get(MetadataRepository.XSD_COLUMN_NUMBER);
        Element xmlElement = (Element) additionalData.get(MetadataRepository.XSD_DOM_ELEMENT);

        TypeMetadata validationType;
        if (containingType != null) {
            validationType = containingType;
        } else {
            int errorCount = handler.getErrorCount();
            containingField.validate(new LocationOverride(containingField, handler, xmlElement, lineNumberObject, columnNumberObject));
            if (handler.getErrorCount() > errorCount) {
                return;
            }
            validationType = containingField.getType();
        }
        if (lineNumberObject == null) {
            lineNumberObject = validationType.<Integer>getData(MetadataRepository.XSD_LINE_NUMBER);
        }
        if (columnNumberObject == null) {
            columnNumberObject = validationType.<Integer>getData(MetadataRepository.XSD_COLUMN_NUMBER);
        }
        if (lineNumberObject == null) {
            lineNumberObject = -1;
        }
        if (columnNumberObject == null) {
            columnNumberObject = -1;
        }
        if (fieldName != null) {
            ComplexTypeMetadata complexTypeMetadata = (ComplexTypeMetadata) validationType;
            int errorCount = handler.getErrorCount();
            complexTypeMetadata.validate(handler);
            if (handler.getErrorCount() > errorCount) {
                return;
            }
            if (!complexTypeMetadata.hasField(fieldName)) {
                handler.error(this,
                        "Type '" + validationType.getName() + "' does not own field '" + fieldName + "'.",
                        xmlElement,
                        lineNumberObject,
                        columnNumberObject,
                        ValidationError.TYPE_DOES_NOT_OWN_FIELD);
            }
        }
    }

    @Override
    public ValidationRule createValidationRule() {
        return ValidationFactory.getRule(this);
    }

    @Override
    public TypeMetadata getDeclaringType() {
        return getField().getDeclaringType();
    }

    @Override
    public void adopt(ComplexTypeMetadata metadata, MetadataRepository repository) {
        FieldMetadata copy = getField().copy(this.repository);
        copy.setContainingType(metadata);
        metadata.addField(copy);
    }

    @Override
    public FieldMetadata copy(MetadataRepository repository) {
        return this;
    }

    @Override
    public List<String> getHideUsers() {
        return getField().getHideUsers();
    }

    @Override
    public List<String> getWriteUsers() {
        return getField().getWriteUsers();
    }
    
    @Override
    public List<String> getWorkflowAccessRights() {
        return getField().getWorkflowAccessRights();
    }

    @Override
    public boolean isMany() {
        return getField().isMany();
    }

    @Override
    public boolean isMandatory() {
        return getField().isMandatory();
    }

    @Override
    public <T> T accept(MetadataVisitor<T> visitor) {
        return getField().accept(visitor);
    }

    @Override
    public String toString() {
        if (containingType != null) {
            return containingType.toString() + "/" + fieldName; //$NON-NLS-1$
        } else {
            return containingField.toString() + "/" + fieldName; //$NON-NLS-1$
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FieldMetadata && getField().equals(o);
    }
}
