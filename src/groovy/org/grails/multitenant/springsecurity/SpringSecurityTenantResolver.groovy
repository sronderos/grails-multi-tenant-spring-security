package org.grails.multitenant.springsecurity

import grails.plugin.multitenant.core.TenantResolver

import javax.servlet.http.HttpServletRequest

import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import org.springframework.security.core.context.SecurityContextHolder

class SpringSecurityTenantResolver implements TenantResolver {
	
	private static final log = LogFactory.getLog(this)
	
	/**
	 * Given a request, returns the associated tenantId
	 * tenantId is retrieved from the Spring Security User Class.
	 *
	 * @param inRequest - The Http request to use to resolve a tenant id.
	 * @return - The tenant id that matches the request.
	 */
	public Integer getTenantFromRequest(HttpServletRequest inRequest) {
		log.debug "Getting TenantId from Spring Security"
		int tenantId = 0
		
		Object principal = SecurityContextHolder.getContext().getAuthentication()?.getPrincipal()
		
		if (principal?.respondsTo('getId')) {
			log.debug "Getting TenantId from Spring Security Principal"
			def userClass = AH.application.getDomainClass(AH.application.config.grails.plugins.springsecurity.userLookup.userDomainClassName)
			def userInstance = userClass.clazz.get(principal.id)
			tenantId = userInstance?.userTenantId
		}
		
		log.debug "Using Tenant Id: ${tenantId}"
		return tenantId
	}
}
