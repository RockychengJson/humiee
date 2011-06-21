/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.DocumentRoot;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * This is the item provider adapter for a {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.DocumentRoot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentRootItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRootItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addGroupPropertyDescriptor(object);
			addUserPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Group feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGroupPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DocumentRoot_group_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_group_feature", "_UI_DocumentRoot_type"),
				 htdPackage.Literals.DOCUMENT_ROOT__GROUP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the User feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUserPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DocumentRoot_user_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_user_feature", "_UI_DocumentRoot_type"),
				 htdPackage.Literals.DOCUMENT_ROOT__USER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__BUSINESS_ADMINISTRATORS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__EXCLUDED_OWNERS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__GROUPS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__HUMAN_INTERACTIONS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__IMPORT);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__LOGICAL_PEOPLE_GROUPS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__NOTIFICATION);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__NOTIFICATIONS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__ORGANIZATIONAL_ENTITY);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__PEOPLE_ASSIGNMENTS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNERS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__PRIORITY);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__RECIPIENTS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__TASK);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__TASK_INITIATOR);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__TASKS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__TASK_STAKEHOLDERS);
			childrenFeatures.add(htdPackage.Literals.DOCUMENT_ROOT__USERS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns DocumentRoot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DocumentRoot"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((DocumentRoot)object).getGroup();
		return label == null || label.length() == 0 ?
			getString("_UI_DocumentRoot_type") :
			getString("_UI_DocumentRoot_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(DocumentRoot.class)) {
			case htdPackage.DOCUMENT_ROOT__GROUP:
			case htdPackage.DOCUMENT_ROOT__USER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case htdPackage.DOCUMENT_ROOT__BUSINESS_ADMINISTRATORS:
			case htdPackage.DOCUMENT_ROOT__EXCLUDED_OWNERS:
			case htdPackage.DOCUMENT_ROOT__GROUPS:
			case htdPackage.DOCUMENT_ROOT__HUMAN_INTERACTIONS:
			case htdPackage.DOCUMENT_ROOT__IMPORT:
			case htdPackage.DOCUMENT_ROOT__LOGICAL_PEOPLE_GROUPS:
			case htdPackage.DOCUMENT_ROOT__NOTIFICATION:
			case htdPackage.DOCUMENT_ROOT__NOTIFICATIONS:
			case htdPackage.DOCUMENT_ROOT__ORGANIZATIONAL_ENTITY:
			case htdPackage.DOCUMENT_ROOT__PEOPLE_ASSIGNMENTS:
			case htdPackage.DOCUMENT_ROOT__POTENTIAL_OWNERS:
			case htdPackage.DOCUMENT_ROOT__PRIORITY:
			case htdPackage.DOCUMENT_ROOT__RECIPIENTS:
			case htdPackage.DOCUMENT_ROOT__TASK:
			case htdPackage.DOCUMENT_ROOT__TASK_INITIATOR:
			case htdPackage.DOCUMENT_ROOT__TASKS:
			case htdPackage.DOCUMENT_ROOT__TASK_STAKEHOLDERS:
			case htdPackage.DOCUMENT_ROOT__USERS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__BUSINESS_ADMINISTRATORS,
				 htdFactory.eINSTANCE.createTGenericHumanRole()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__EXCLUDED_OWNERS,
				 htdFactory.eINSTANCE.createTGenericHumanRole()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__GROUPS,
				 htdFactory.eINSTANCE.createTGrouplist()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__HUMAN_INTERACTIONS,
				 htdFactory.eINSTANCE.createTHumanInteractions()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__IMPORT,
				 htdFactory.eINSTANCE.createTImport()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__LOGICAL_PEOPLE_GROUPS,
				 htdFactory.eINSTANCE.createTLogicalPeopleGroups()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__NOTIFICATION,
				 htdFactory.eINSTANCE.createTNotification()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__NOTIFICATIONS,
				 htdFactory.eINSTANCE.createTNotifications()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__ORGANIZATIONAL_ENTITY,
				 htdFactory.eINSTANCE.createTOrganizationalEntity()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__PEOPLE_ASSIGNMENTS,
				 htdFactory.eINSTANCE.createTPeopleAssignments()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNERS,
				 htdFactory.eINSTANCE.createTGenericHumanRole()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__PRIORITY,
				 htdFactory.eINSTANCE.createTPriority()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__RECIPIENTS,
				 htdFactory.eINSTANCE.createTGenericHumanRole()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__TASK,
				 htdFactory.eINSTANCE.createTTask()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__TASK_INITIATOR,
				 htdFactory.eINSTANCE.createTGenericHumanRole()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__TASKS,
				 htdFactory.eINSTANCE.createTTasks()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__TASK_STAKEHOLDERS,
				 htdFactory.eINSTANCE.createTGenericHumanRole()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.DOCUMENT_ROOT__USERS,
				 htdFactory.eINSTANCE.createTUserlist()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == htdPackage.Literals.DOCUMENT_ROOT__BUSINESS_ADMINISTRATORS ||
			childFeature == htdPackage.Literals.DOCUMENT_ROOT__EXCLUDED_OWNERS ||
			childFeature == htdPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNERS ||
			childFeature == htdPackage.Literals.DOCUMENT_ROOT__RECIPIENTS ||
			childFeature == htdPackage.Literals.DOCUMENT_ROOT__TASK_INITIATOR ||
			childFeature == htdPackage.Literals.DOCUMENT_ROOT__TASK_STAKEHOLDERS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return WSHumanTaskSchemaEditPlugin.INSTANCE;
	}

}
