package com.vinay.learning.spring;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ComponentScanBeanDefinitionParser;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.TypeFilter;

public class AbstractProxyBeanDefinitionParser extends ComponentScanBeanDefinitionParser {
	private boolean proxyPerInterface = false;

	private void registerComponent(BeanDefinitionRegistry registry, Collection<BeanDefinitionHolder> holder,
			String targetClass, String beanName) {
		if (isProxyPerInterface())
			buildProxyPerInterface(registry, holder, targetClass);
		else
			buildSingleProxy(registry, holder, targetClass, beanName);

	}

	private void buildSingleProxy(BeanDefinitionRegistry registry, Collection<BeanDefinitionHolder> holder,
			String targetName, String beanName) {
		String[] interfacesName = new String[holder.size()];
		int i = 0;
		for (BeanDefinitionHolder bdh : holder) {
			interfacesName[i++] = bdh.getBeanDefinition().getBeanClassName();
		}
		registerProxyBean(interfacesName, beanName, interfacesName, targetName, registry, false);
	}

	private void buildProxyPerInterface(BeanDefinitionRegistry registry, Collection<BeanDefinitionHolder> holder,
			String targetName) {
		holder.forEach(beanDef -> {
			String beanClassName = beanDef.getBeanDefinition().getBeanClassName();
			String[] alias = new String[] { beanClassName };
			String[] proxyInterface = new String[] { beanClassName };
			String beanName = beanClassName +"Proxy";
			boolean primary = beanDef.getBeanDefinition().isPrimary();
			registerProxyBean(proxyInterface, beanName, alias, targetName, registry, primary);

		});

	}

	private void registerProxyBean(String[] proxyInterface, String beanName, String[] alias, String targetName,
			BeanDefinitionRegistry registry, boolean primary) {
		BeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(ProxyFactoryBean.class)
				.addPropertyValue("proxyInterfaces", proxyInterface).addPropertyValue("targetName", targetName)
				.getBeanDefinition();
		bd.setPrimary(primary);
		BeanDefinitionHolder holder = new BeanDefinitionHolder(bd, beanName, alias);
		BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);

	}

	protected void parseAndRegister(BeanDefinitionRegistry registry, Collection<TypeFilter> includeFilter,
			Collection<TypeFilter> excludeFilter, Collection<String> basePackages, String targetName, String beanName) {
		Collection<BeanDefinitionHolder> holderset = getHolderSet(basePackages, includeFilter, excludeFilter);
		registerComponent(registry, holderset, targetName, beanName);
	}

	private Collection<BeanDefinitionHolder> getHolderSet(Collection<String> basePackages,
			Collection<TypeFilter> includeFilter, Collection<TypeFilter> excludeFilter) {
		Collection<BeanDefinitionHolder> holderSet = new HashSet<>();
		ClassPathInterfaceScanner sc = new ClassPathInterfaceScanner();
		if (includeFilter != null) {
			includeFilter.forEach(sc::addIncludeFilter);
		}
		if (excludeFilter != null) {
			excludeFilter.forEach(sc::addExcludeFilter);
		}
		basePackages.forEach(pkg -> {
			Set<BeanDefinition> candidateComponents = sc.findCandidateComponents(pkg);
			candidateComponents.forEach(beanDef -> {
				if (beanDef instanceof ScannedGenericBeanDefinition) {
					AnnotationMetadata metaData = ((ScannedGenericBeanDefinition) beanDef).getMetadata();
					beanDef.setPrimary(metaData.hasAnnotation(Primary.class.getName()));

				}
				String alis = beanDef.getBeanClassName();
				BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDef, alis, new String[] { alis });
				holderSet.add(holder);
			});

		});
		return holderSet;
	}

	public boolean isProxyPerInterface() {
		return proxyPerInterface;
	}

	public void setProxyPerInterface(boolean proxyPerInterface) {
		this.proxyPerInterface = proxyPerInterface;
	}

}
