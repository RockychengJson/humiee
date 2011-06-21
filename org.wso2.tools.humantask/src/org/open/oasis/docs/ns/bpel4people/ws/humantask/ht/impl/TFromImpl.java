/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TArgument;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TFrom;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLiteral;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TFrom</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TFromImpl#getArgument <em>Argument</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TFromImpl#getLiteral <em>Literal</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TFromImpl#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TFromImpl#getLogicalPeopleGroup <em>Logical People Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TFromImpl extends TExtensibleMixedContentElementsImpl implements TFrom {
	/**
	 * The default value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionLanguage()
	 * @generated
	 * @ordered
	 */
	protected String expressionLanguage = EXPRESSION_LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLogicalPeopleGroup() <em>Logical People Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalPeopleGroup()
	 * @generated
	 * @ordered
	 */
	protected static final QName LOGICAL_PEOPLE_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLogicalPeopleGroup() <em>Logical People Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalPeopleGroup()
	 * @generated
	 * @ordered
	 */
	protected QName logicalPeopleGroup = LOGICAL_PEOPLE_GROUP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TFromImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return htdPackage.Literals.TFROM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TArgument getArgument() {
		return (TArgument)getMixed().get(htdPackage.Literals.TFROM__ARGUMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArgument(TArgument newArgument, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(htdPackage.Literals.TFROM__ARGUMENT, newArgument, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArgument(TArgument newArgument) {
		((FeatureMap.Internal)getMixed()).set(htdPackage.Literals.TFROM__ARGUMENT, newArgument);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLiteral getLiteral() {
		return (TLiteral)getMixed().get(htdPackage.Literals.TFROM__LITERAL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLiteral(TLiteral newLiteral, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(htdPackage.Literals.TFROM__LITERAL, newLiteral, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLiteral(TLiteral newLiteral) {
		((FeatureMap.Internal)getMixed()).set(htdPackage.Literals.TFROM__LITERAL, newLiteral);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpressionLanguage() {
		return expressionLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpressionLanguage(String newExpressionLanguage) {
		String oldExpressionLanguage = expressionLanguage;
		expressionLanguage = newExpressionLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TFROM__EXPRESSION_LANGUAGE, oldExpressionLanguage, expressionLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getLogicalPeopleGroup() {
		return logicalPeopleGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogicalPeopleGroup(QName newLogicalPeopleGroup) {
		QName oldLogicalPeopleGroup = logicalPeopleGroup;
		logicalPeopleGroup = newLogicalPeopleGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TFROM__LOGICAL_PEOPLE_GROUP, oldLogicalPeopleGroup, logicalPeopleGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case htdPackage.TFROM__ARGUMENT:
				return basicSetArgument(null, msgs);
			case htdPackage.TFROM__LITERAL:
				return basicSetLiteral(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case htdPackage.TFROM__ARGUMENT:
				return getArgument();
			case htdPackage.TFROM__LITERAL:
				return getLiteral();
			case htdPackage.TFROM__EXPRESSION_LANGUAGE:
				return getExpressionLanguage();
			case htdPackage.TFROM__LOGICAL_PEOPLE_GROUP:
				return getLogicalPeopleGroup();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case htdPackage.TFROM__ARGUMENT:
				setArgument((TArgument)newValue);
				return;
			case htdPackage.TFROM__LITERAL:
				setLiteral((TLiteral)newValue);
				return;
			case htdPackage.TFROM__EXPRESSION_LANGUAGE:
				setExpressionLanguage((String)newValue);
				return;
			case htdPackage.TFROM__LOGICAL_PEOPLE_GROUP:
				setLogicalPeopleGroup((QName)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case htdPackage.TFROM__ARGUMENT:
				setArgument((TArgument)null);
				return;
			case htdPackage.TFROM__LITERAL:
				setLiteral((TLiteral)null);
				return;
			case htdPackage.TFROM__EXPRESSION_LANGUAGE:
				setExpressionLanguage(EXPRESSION_LANGUAGE_EDEFAULT);
				return;
			case htdPackage.TFROM__LOGICAL_PEOPLE_GROUP:
				setLogicalPeopleGroup(LOGICAL_PEOPLE_GROUP_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case htdPackage.TFROM__ARGUMENT:
				return getArgument() != null;
			case htdPackage.TFROM__LITERAL:
				return getLiteral() != null;
			case htdPackage.TFROM__EXPRESSION_LANGUAGE:
				return EXPRESSION_LANGUAGE_EDEFAULT == null ? expressionLanguage != null : !EXPRESSION_LANGUAGE_EDEFAULT.equals(expressionLanguage);
			case htdPackage.TFROM__LOGICAL_PEOPLE_GROUP:
				return LOGICAL_PEOPLE_GROUP_EDEFAULT == null ? logicalPeopleGroup != null : !LOGICAL_PEOPLE_GROUP_EDEFAULT.equals(logicalPeopleGroup);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (expressionLanguage: ");
		result.append(expressionLanguage);
		result.append(", logicalPeopleGroup: ");
		result.append(logicalPeopleGroup);
		result.append(')');
		return result.toString();
	}

} //TFromImpl
