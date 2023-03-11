@Library('jslcodso')
import codeso.build.BuildManager
import codeso.parser.JSONRequestParser


pipeline {
    agent none
    
    stages {
        stage('Initializing') {
            steps {
                node('built-in') {
                    script {
                        def paramSpec = readJSON text: params.COPTIONS
                        def cjp = new JSONRequestParser(steps, paramSpec)
                        def res = cjp.populateStagesDetail()
                        println("Going for loop")
                        bldMgr = new BuildManager(steps)
                        for (stg in res.keySet()) {
                            stage(stg) {
                                bldMgr.process(res[stg])
                            }
                        }
                    }
                }
            }
        }
    }
}
