@Library('jslcodso')
import codeso.Manager
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
                        mgr = new Manager()
                        for (stg in res.keySet()) {
                            stage(stg) {
                                //Send this Value (Map) to the runner class to execute the action
                                res[stg].each{entry -> println "$entry.value"}
                                mgr.process(stg)
                            }
                        }
                    }
                }
            }
        }
    }
}
