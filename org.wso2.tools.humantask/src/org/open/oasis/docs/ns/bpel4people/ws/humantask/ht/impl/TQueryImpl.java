/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TQuery;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TQuery</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TQueryImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TQueryImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TQueryImpl#getPart <em>Part</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TQueryImpl#getQueryLanguage <em>Query Language</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TQueryImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TQueryImpl extends EObjectImpl implements TQuery {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The default value of the '{@link #getPart() <em>Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPart()
	 * @generated
	 * @ordered
	 */
	protected static final Object PART_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPart() <em>Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPart()
	 * @generated
	 * @ordered
	 */
	protected Object part = PART_EDEFAULT;

	/**
	 * The default value of the '{@link #getQueryLanguage() <em>Query Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String QUERY_LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQueryLanguage() <em>Query Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryLanguage()
	 * @generated
	 * @ordered
	 */
	protected String queryLanguage = QUERY_LANGUAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnyAttribute()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap anyAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TQueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return htdPackage.Literals.TQUERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, htdPackage.TQUERY__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAny() {
		return (FeatureMap)getMixed().<FeatureMap.Entry>list(htdPackage.Literals.TQUERY__ANY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getPart() {
		return part;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPart(Object newPart) {
		Object oldPart = part;
		part = newPart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TQUERY__PART, oldPart, part));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQueryLanguage() {
		return queryLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueryLanguage(String newQueryLanguage) {
		String oldQueryLanguage = queryLanguage;
		queryLanguage = newQueryLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TQUERY__QUERY_LANGUAGE, oldQueryLanguage, queryLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, htdPackage.TQUERY__ANY_ATTRIBUTE);
		}
		return anyAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case htdPackage.TQUERY__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case htdPackage.TQUERY__ANY:
				return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
			case htdPackage.TQUERY__ANY_ATTRIBUTE:
				return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
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
			case htdPackage.TQUERY__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case htdPackage.TQUERY__ANY:
				if (coreType) return getAny();
				return ((FeatureMap.Internal)getAny()).getWrapper();
			case htdPackage.TQUERY__PART:
				return getPart();
			case htdPackage.TQUERY__QUERY_LANGUAGE:
				return getQueryLanguage();
			case htdPackage.TQUERY__ANY_ATTRIBUTE:
				if (coreType) return getAnyAttribute();
				return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
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
			case htdPackage.TQUERY__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case htdPackage.TQUERY__ANY:
				((FeatureMap.Internal)getAny()).set(newValue);
				return;
			case htdPackage.TQUERY__PART:
				setPart(newValue);
				return;
			case htdPackage.TQUERY__QUERY_LANGUAGE:
				setQueryLanguage((String)newValue);
				return;
			case htdPackage.TQUERY__ANY_ATTRIBUTE:
				((FeatureMap.Internal)getAnyAttribute()).set(newValue);
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
			case htdPackage.TQUERY__MIXED:
				getMixed().clear();
				return;
			case htdPackage.TQUERY__ANY:
				getAny().clear();
				return;
			case htdPackage.TQUERY__PART:
				setPart(PART_EDEFAULT);
				return;
			case htdPackage.TQUERY__QUERY_LANGUAGE:
				setQueryLanguage(QUERY_LANGUAGE_EDEFAULT);
				return;
			case htdPackage.TQUERY__ANY_ATTRIBUTE:
				getAnyAttribute().clear();
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
			case htdPackage.TQUERY__MIXED:
				return mixed != null && !mixed.isEmpty();
			case htdPackage.TQUERY__ANY:
				return !getAny().isEmpty();
			case htdPackage.TQUERY__PART:
				return PART_EDEFAULT == null ? part != null : !PART_EDEFAULT.equals(part);
			case htdPackage.TQUERY__QUERY_LANGUAGE:
				return QUERY_LANGUAGE_EDEFAULT == null ? queryLanguage != null : !QUERY_LANGUAGE_EDEFAULT.equals(queryLanguage);
			case htdPackage.TQUERY__ANY_ATTRIBUTE:
				return anyAttribute != null && !anyAttribute.isEmpty();
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", part: ");
		result.append(part);
		result.append(", queryLanguage: ");
		result.append(queryLanguage);
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //TQueryImpl
