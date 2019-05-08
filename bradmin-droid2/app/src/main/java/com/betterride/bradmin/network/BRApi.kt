package com.betterride.bradmin.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class BRApi {
    companion object {
        val baseUrl = "https://srv-desa.eastus2.cloudapp.azure.com/appbetterride/api"
        val supervisor = "$baseUrl/v1/supervisors"
        val validateuser = "$baseUrl/v1/login/user/{username}/pass/{password}"
        val organization = "$baseUrl/v1/organizations"
        val allprojects =  "$baseUrl/v1/projects/supervisors/{supervisor_id}"
        val addprojects =  "$baseUrl/v1/project"
        val deleteproject = "$baseUrl/v1/projects/{id}"
        val sessionsall= "$baseUrl/v1/sessions/projects/{project_id}"
        val operatorsbySession = "$baseUrl/v1/userSession/sessions/{session_id}"
        val operatorsbyOrganization = "$baseUrl/v1/operators/organizations/{organization_id}"


        fun requestGetProjects(supervisor: String,
            responseHandler: (ResponseProject?) -> Unit,
            errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.allprojects)
                .addPathParameter("supervisor_id",supervisor)
                .addHeaders("token","1234")
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseProject::class.java, object : ParsedRequestListener<ResponseProject> {
                    override fun onResponse(response: ResponseProject?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }

        fun requestGetOperatorsbySession(organization: String,
                               responseHandler: (ResponseOperator?) -> Unit,
                               errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.operatorsbyOrganization)
                .addPathParameter("organization_id",organization)
                .addHeaders("token","1234")
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseOperator::class.java, object : ParsedRequestListener<ResponseOperator> {
                    override fun onResponse(response: ResponseOperator?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }

        fun requestGetOperatorsbyOrganization(session: String,
                                         responseHandler: (ResponseOperator?) -> Unit,
                                         errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.operatorsbySession)
                .addPathParameter("session_id",session)
                .addHeaders("token","1234")
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseOperator::class.java, object : ParsedRequestListener<ResponseOperator> {
                    override fun onResponse(response: ResponseOperator?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }

        fun requestPostSupervisor(name: String,lastname: String,email: String,username: String,
                                  password: String,organization_id: String,role: String,genre: String, token: String,
                                  responseHandler: (ResponseBasic?) -> Unit,
                                  errorHandler: (ANError?) -> Unit
        ) {
            val data = JSONObject()
            try {
                data.put("name", name)
                data.put("last_name", lastname)
                data.put("email", email)
                data.put("username", username)
                data.put("password", password)
                data.put("organization_id", organization_id)
                data.put("role", role)
                data.put("gender", genre)

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(BRApi.supervisor)
                .addHeaders("token", token)
                .addJSONObjectBody(data)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseBasic::class.java, object : ParsedRequestListener<ResponseBasic>{
                    override fun onResponse(response: ResponseBasic?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestPostSupervisorValidate(username: String,
                                          password: String,
                                          responseHandler: (ResponseSupervisor?) -> Unit,
                                          errorHandler: (ANError?) -> Unit
        ) {

            AndroidNetworking.post(BRApi.validateuser)
                .addPathParameter("username",username)
                .addPathParameter("password", password)
                .addHeaders("token", "1234")
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseSupervisor::class.java, object : ParsedRequestListener<ResponseSupervisor>{
                    override fun onResponse(response: ResponseSupervisor?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestPostAddProject(name: String, date: Date,supervisor_id: String,
                                  responseHandler: (ResponseBasic?) -> Unit,
                                  errorHandler: (ANError?) -> Unit
        ) {
            val data = JSONObject()
            try {
                data.put("name", name)
                data.put("date",date.toString())
                data.put("supervisor_id", supervisor_id)

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(BRApi.addprojects)
                .addHeaders("token", "1234")
                .addJSONObjectBody(data)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseBasic::class.java, object : ParsedRequestListener<ResponseBasic>{
                    override fun onResponse(response: ResponseBasic?) {
                        responseHandler(response)
                    }
                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestPostOrganizationData(token: String,
                                        responseHandler: (ResponseOrganization?) -> Unit,
                                        errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.organization)
                .addHeaders("token", token)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseOrganization::class.java, object : ParsedRequestListener<ResponseOrganization>{
                    override fun onResponse(response: ResponseOrganization?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestDeleteOrganization(id: String,token: String,
                                        responseHandler: (ResponseBasic?) -> Unit,
                                        errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.delete(BRApi.deleteproject)
                .addPathParameter("id",id)
                .addHeaders("token", token)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseBasic::class.java, object : ParsedRequestListener<ResponseBasic>{
                    override fun onResponse(response: ResponseBasic?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestGetSessions(id: String,token: String,
                                      responseHandler: (ResponseSession?) -> Unit,
                                      errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.sessionsall)
                .addPathParameter("project_id",id)
                .addHeaders("token", token)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseSession::class.java, object : ParsedRequestListener<ResponseSession>{
                    override fun onResponse(response: ResponseSession?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
    }
}