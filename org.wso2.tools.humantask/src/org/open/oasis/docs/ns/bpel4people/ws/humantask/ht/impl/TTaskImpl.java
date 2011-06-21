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

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadlines;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDelegation;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExpression;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPeopleAssignments;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPriority;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TQuery;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TRenderings;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTaskInterface;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TTask</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getPeopleAssignments <em>People Assignments</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getDelegation <em>Delegation</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getPresentationElements <em>Presentation Elements</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getOutcome <em>Outcome</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getSearchBy <em>Search By</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getRenderings <em>Renderings</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getDeadlines <em>Deadlines</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TTaskImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TTaskImpl extends TExtensibleElementsImpl implements TTask {
	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected TTaskInterface interface_;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected TPriority priority;

	/**
	 * The cached value of the '{@link #getPeopleAssignments() <em>People Assignments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeopleAssignments()
	 * @generated
	 * @ordered
	 */
	protected TPeopleAssignments peopleAssignments;

	/**
	 * The cached value of the '{@link #getDelegation() <em>Delegation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegation()
	 * @generated
	 * @ordered
	 */
	protected TDelegation delegation;

	/**
	 * The cached value of the '{@link #getPresentationElements() <em>Presentation Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresentationElements()
	 * @generated
	 * @ordered
	 */
	protected TPresentationElements presentationElements;

	/**
	 * The cached value of the '{@link #getOutcome() <em>Outcome</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutcome()
	 * @generated
	 * @ordered
	 */
	protected TQuery outcome;

	/**
	 * The cached value of the '{@link #getSearchBy() <em>Search By</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchBy()
	 * @generated
	 * @ordered
	 */
	protected TExpression searchBy;

	/**
	 * The cached value of the '{@link #getRenderings() <em>Renderings</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderings()
	 * @generated
	 * @ordered
	 */
	protected TRenderings renderings;

	/**
	 * The cached value of the '{@link #getDeadlines() <em>Deadlines</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadlines()
	 * @generated
	 * @ordered
	 */
	protected TDeadlines deadlines;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return htdPackage.Literals.TTASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTaskInterface getInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterface(TTaskInterface newInterface, NotificationChain msgs) {
		TTaskInterface oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__INTERFACE, oldInterface, newInterface);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(TTaskInterface newInterface) {
		if (newInterface != interface_) {
			NotificationChain msgs = null;
			if (interface_ != null)
				msgs = ((InternalEObject)interface_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__INTERFACE, null, msgs);
			if (newInterface != null)
				msgs = ((InternalEObject)newInterface).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__INTERFACE, null, msgs);
			msgs = basicSetInterface(newInterface, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__INTERFACE, newInterface, newInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPriority getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPriority(TPriority newPriority, NotificationChain msgs) {
		TPriority oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__PRIORITY, oldPriority, newPriority);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(TPriority newPriority) {
		if (newPriority != priority) {
			NotificationChain msgs = null;
			if (priority != null)
				msgs = ((InternalEObject)priority).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__PRIORITY, null, msgs);
			if (newPriority != null)
				msgs = ((InternalEObject)newPriority).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__PRIORITY, null, msgs);
			msgs = basicSetPriority(newPriority, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__PRIORITY, newPriority, newPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPeopleAssignments getPeopleAssignments() {
		return peopleAssignments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPeopleAssignments(TPeopleAssignments newPeopleAssignments, NotificationChain msgs) {
		TPeopleAssignments oldPeopleAssignments = peopleAssignments;
		peopleAssignments = newPeopleAssignments;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__PEOPLE_ASSIGNMENTS, oldPeopleAssignments, newPeopleAssignments);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeopleAssignments(TPeopleAssignments newPeopleAssignments) {
		if (newPeopleAssignments != peopleAssignments) {
			NotificationChain msgs = null;
			if (peopleAssignments != null)
				msgs = ((InternalEObject)peopleAssignments).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__PEOPLE_ASSIGNMENTS, null, msgs);
			if (newPeopleAssignments != null)
				msgs = ((InternalEObject)newPeopleAssignments).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__PEOPLE_ASSIGNMENTS, null, msgs);
			msgs = basicSetPeopleAssignments(newPeopleAssignments, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__PEOPLE_ASSIGNMENTS, newPeopleAssignments, newPeopleAssignments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDelegation getDelegation() {
		return delegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDelegation(TDelegation newDelegation, NotificationChain msgs) {
		TDelegation oldDelegation = delegation;
		delegation = newDelegation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__DELEGATION, oldDelegation, newDelegation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelegation(TDelegation newDelegation) {
		if (newDelegation != delegation) {
			NotificationChain msgs = null;
			if (delegation != null)
				msgs = ((InternalEObject)delegation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__DELEGATION, null, msgs);
			if (newDelegation != null)
				msgs = ((InternalEObject)newDelegation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__DELEGATION, null, msgs);
			msgs = basicSetDelegation(newDelegation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__DELEGATION, newDelegation, newDelegation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPresentationElements getPresentationElements() {
		return presentationElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPresentationElements(TPresentationElements newPresentationElements, NotificationChain msgs) {
		TPresentationElements oldPresentationElements = presentationElements;
		presentationElements = newPresentationElements;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__PRESENTATION_ELEMENTS, oldPresentationElements, newPresentationElements);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPresentationElements(TPresentationElements newPresentationElements) {
		if (newPresentationElements != presentationElements) {
			NotificationChain msgs = null;
			if (presentationElements != null)
				msgs = ((InternalEObject)presentationElements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__PRESENTATION_ELEMENTS, null, msgs);
			if (newPresentationElements != null)
				msgs = ((InternalEObject)newPresentationElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__PRESENTATION_ELEMENTS, null, msgs);
			msgs = basicSetPresentationElements(newPresentationElements, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__PRESENTATION_ELEMENTS, newPresentationElements, newPresentationElements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TQuery getOutcome() {
		return outcome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutcome(TQuery newOutcome, NotificationChain msgs) {
		TQuery oldOutcome = outcome;
		outcome = newOutcome;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__OUTCOME, oldOutcome, newOutcome);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutcome(TQuery newOutcome) {
		if (newOutcome != outcome) {
			NotificationChain msgs = null;
			if (outcome != null)
				msgs = ((InternalEObject)outcome).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__OUTCOME, null, msgs);
			if (newOutcome != null)
				msgs = ((InternalEObject)newOutcome).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__OUTCOME, null, msgs);
			msgs = basicSetOutcome(newOutcome, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__OUTCOME, newOutcome, newOutcome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getSearchBy() {
		return searchBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearchBy(TExpression newSearchBy, NotificationChain msgs) {
		TExpression oldSearchBy = searchBy;
		searchBy = newSearchBy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__SEARCH_BY, oldSearchBy, newSearchBy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSearchBy(TExpression newSearchBy) {
		if (newSearchBy != searchBy) {
			NotificationChain msgs = null;
			if (searchBy != null)
				msgs = ((InternalEObject)searchBy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__SEARCH_BY, null, msgs);
			if (newSearchBy != null)
				msgs = ((InternalEObject)newSearchBy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__SEARCH_BY, null, msgs);
			msgs = basicSetSearchBy(newSearchBy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__SEARCH_BY, newSearchBy, newSearchBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRenderings getRenderings() {
		return renderings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderings(TRenderings newRenderings, NotificationChain msgs) {
		TRenderings oldRenderings = renderings;
		renderings = newRenderings;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__RENDERINGS, oldRenderings, newRenderings);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderings(TRenderings newRenderings) {
		if (newRenderings != renderings) {
			NotificationChain msgs = null;
			if (renderings != null)
				msgs = ((InternalEObject)renderings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__RENDERINGS, null, msgs);
			if (newRenderings != null)
				msgs = ((InternalEObject)newRenderings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__RENDERINGS, null, msgs);
			msgs = basicSetRenderings(newRenderings, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__RENDERINGS, newRenderings, newRenderings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDeadlines getDeadlines() {
		return deadlines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeadlines(TDeadlines newDeadlines, NotificationChain msgs) {
		TDeadlines oldDeadlines = deadlines;
		deadlines = newDeadlines;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__DEADLINES, oldDeadlines, newDeadlines);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeadlines(TDeadlines newDeadlines) {
		if (newDeadlines != deadlines) {
			NotificationChain msgs = null;
			if (deadlines != null)
				msgs = ((InternalEObject)deadlines).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__DEADLINES, null, msgs);
			if (newDeadlines != null)
				msgs = ((InternalEObject)newDeadlines).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - htdPackage.TTASK__DEADLINES, null, msgs);
			msgs = basicSetDeadlines(newDeadlines, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__DEADLINES, newDeadlines, newDeadlines));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, htdPackage.TTASK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case htdPackage.TTASK__INTERFACE:
				return basicSetInterface(null, msgs);
			case htdPackage.TTASK__PRIORITY:
				return basicSetPriority(null, msgs);
			case htdPackage.TTASK__PEOPLE_ASSIGNMENTS:
				return basicSetPeopleAssignments(null, msgs);
			case htdPackage.TTASK__DELEGATION:
				return basicSetDelegation(null, msgs);
			case htdPackage.TTASK__PRESENTATION_ELEMENTS:
				return basicSetPresentationElements(null, msgs);
			case htdPackage.TTASK__OUTCOME:
				return basicSetOutcome(null, msgs);
			case htdPackage.TTASK__SEARCH_BY:
				return basicSetSearchBy(null, msgs);
			case htdPackage.TTASK__RENDERINGS:
				return basicSetRenderings(null, msgs);
			case htdPackage.TTASK__DEADLINES:
				return basicSetDeadlines(null, msgs);
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
			case htdPackage.TTASK__INTERFACE:
				return getInterface();
			case htdPackage.TTASK__PRIORITY:
				return getPriority();
			case htdPackage.TTASK__PEOPLE_ASSIGNMENTS:
				return getPeopleAssignments();
			case htdPackage.TTASK__DELEGATION:
				return getDelegation();
			case htdPackage.TTASK__PRESENTATION_ELEMENTS:
				return getPresentationElements();
			case htdPackage.TTASK__OUTCOME:
				return getOutcome();
			case htdPackage.TTASK__SEARCH_BY:
				return getSearchBy();
			case htdPackage.TTASK__RENDERINGS:
				return getRenderings();
			case htdPackage.TTASK__DEADLINES:
				return getDeadlines();
			case htdPackage.TTASK__NAME:
				return getName();
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
			case htdPackage.TTASK__INTERFACE:
				setInterface((TTaskInterface)newValue);
				return;
			case htdPackage.TTASK__PRIORITY:
				setPriority((TPriority)newValue);
				return;
			case htdPackage.TTASK__PEOPLE_ASSIGNMENTS:
				setPeopleAssignments((TPeopleAssignments)newValue);
				return;
			case htdPackage.TTASK__DELEGATION:
				setDelegation((TDelegation)newValue);
				return;
			case htdPackage.TTASK__PRESENTATION_ELEMENTS:
				setPresentationElements((TPresentationElements)newValue);
				return;
			case htdPackage.TTASK__OUTCOME:
				setOutcome((TQuery)newValue);
				return;
			case htdPackage.TTASK__SEARCH_BY:
				setSearchBy((TExpression)newValue);
				return;
			case htdPackage.TTASK__RENDERINGS:
				setRenderings((TRenderings)newValue);
				return;
			case htdPackage.TTASK__DEADLINES:
				setDeadlines((TDeadlines)newValue);
				return;
			case htdPackage.TTASK__NAME:
				setName((String)newValue);
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
			case htdPackage.TTASK__INTERFACE:
				setInterface((TTaskInterface)null);
				return;
			case htdPackage.TTASK__PRIORITY:
				setPriority((TPriority)null);
				return;
			case htdPackage.TTASK__PEOPLE_ASSIGNMENTS:
				setPeopleAssignments((TPeopleAssignments)null);
				return;
			case htdPackage.TTASK__DELEGATION:
				setDelegation((TDelegation)null);
				return;
			case htdPackage.TTASK__PRESENTATION_ELEMENTS:
				setPresentationElements((TPresentationElements)null);
				return;
			case htdPackage.TTASK__OUTCOME:
				setOutcome((TQuery)null);
				return;
			case htdPackage.TTASK__SEARCH_BY:
				setSearchBy((TExpression)null);
				return;
			case htdPackage.TTASK__RENDERINGS:
				setRenderings((TRenderings)null);
				return;
			case htdPackage.TTASK__DEADLINES:
				setDeadlines((TDeadlines)null);
				return;
			case htdPackage.TTASK__NAME:
				setName(NAME_EDEFAULT);
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
			case htdPackage.TTASK__INTERFACE:
				return interface_ != null;
			case htdPackage.TTASK__PRIORITY:
				return priority != null;
			case htdPackage.TTASK__PEOPLE_ASSIGNMENTS:
				return peopleAssignments != null;
			case htdPackage.TTASK__DELEGATION:
				return delegation != null;
			case htdPackage.TTASK__PRESENTATION_ELEMENTS:
				return presentationElements != null;
			case htdPackage.TTASK__OUTCOME:
				return outcome != null;
			case htdPackage.TTASK__SEARCH_BY:
				return searchBy != null;
			case htdPackage.TTASK__RENDERINGS:
				return renderings != null;
			case htdPackage.TTASK__DEADLINES:
				return deadlines != null;
			case htdPackage.TTASK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TTaskImpl
