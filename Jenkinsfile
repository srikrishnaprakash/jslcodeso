@Library('codso-jsl')
import codeso.build.BuildManager
import codeso.sca.SCAManager


pipeline {
    agent none

    stages {
        stage('Initializing') {
            steps {
                node('built-in') {
                    script {
                        println("Starting the preparation..... ")
                        iParams = readJSON text: params.COPTIONS
					}
				}
			}
		}
		stage('Preparing') {
			steps {
				podTemplate(label: 'msscodeso') {
					node('msscodeso') {
						script {
							if (iParams.bld != null) {
								BuildManager bldMgr = new BuildManager(steps)
								bldMgr.execute(iParams.bld)
							}
							if (iParams.sca != null) {
								SCAManager scaMgr = new SCAManager(steps)
								scaMgr.execute(iParams.sca)
							}
						}
					}
				}
			}
		}
    }
}
