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

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLiteral;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * This is the item provider adapter for a {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLiteral} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TLiteralItemProvider
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
	public TLiteralItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(htdPackage.Literals.TLITERAL__MIXED);
			childrenFeatures.add(htdPackage.Literals.TLITERAL__ANY_ATTRIBUTE);
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
	 * This returns TLiteral.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TLiteral"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_TLiteral_type");
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

		switch (notification.getFeatureID(TLiteral.class)) {
			case htdPackage.TLITERAL__MIXED:
			case htdPackage.TLITERAL__ANY_ATTRIBUTE:
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
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION,
					 XMLTypeFactory.eINSTANCE.createProcessingInstruction())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__BUSINESS_ADMINISTRATORS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__EXCLUDED_OWNERS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__GROUP,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__GROUPS,
					 htdFactory.eINSTANCE.createTGrouplist())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__HUMAN_INTERACTIONS,
					 htdFactory.eINSTANCE.createTHumanInteractions())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__IMPORT,
					 htdFactory.eINSTANCE.createTImport())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__LOGICAL_PEOPLE_GROUPS,
					 htdFactory.eINSTANCE.createTLogicalPeopleGroups())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__NOTIFICATION,
					 htdFactory.eINSTANCE.createTNotification())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__NOTIFICATIONS,
					 htdFactory.eINSTANCE.createTNotifications())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__ORGANIZATIONAL_ENTITY,
					 htdFactory.eINSTANCE.createTOrganizationalEntity())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__PEOPLE_ASSIGNMENTS,
					 htdFactory.eINSTANCE.createTPeopleAssignments())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNERS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__PRIORITY,
					 htdFactory.eINSTANCE.createTPriority())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__RECIPIENTS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__TASK,
					 htdFactory.eINSTANCE.createTTask())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__TASK_INITIATOR,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__TASKS,
					 htdFactory.eINSTANCE.createTTasks())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__TASK_STAKEHOLDERS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__USER,
					 "")));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TLITERAL__MIXED,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.DOCUMENT_ROOT__USERS,
					 htdFactory.eINSTANCE.createTUserlist())));
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

		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry)childObject;
			childFeature = entry.getEStructuralFeature();
			childObject = entry.getValue();
		}

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
