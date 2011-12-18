/**
 * This module defines the build dependencies for the plugin to support such items as the OSCache functionality, etc.
 */
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits "global"
	
	log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {
        mavenCentral()
        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
		
        grailsPlugins()
        grailsHome()
        grailsCentral()
	}
	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
		
	}

	plugins {
		compile ':falcone-util:1.0'
		compile ':spring-security-core:1.0.1'
		compile ':multi-tenant-core:1.0.0'
		
		compile(":release:1.0.0") {
            export = false
        }
	}
}
