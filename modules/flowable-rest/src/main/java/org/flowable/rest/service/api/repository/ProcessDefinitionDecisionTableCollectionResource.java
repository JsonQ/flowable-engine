/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.rest.service.api.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.flowable.dmn.api.DmnDecisionTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yvo Swillens
 */
@RestController
@Api(tags = { "Process Definitions" }, description = "Manage Process Definitions", authorizations = { @Authorization(value = "basicAuth") })
public class ProcessDefinitionDecisionTableCollectionResource extends BaseProcessDefinitionResource {

    @ApiOperation(value = "List decision tables for a process-definition", nickname = "listProcessDefinitionDecisionTables", tags = { "Process Definitions" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Indicates the process definition was found and the decision tables are returned.", response = DecisionTableResponse.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Indicates the requested process definition was not found.")
    })
    @GetMapping(value = "/repository/process-definitions/{processDefinitionId}/decision-tables", produces = "application/json")
    public List<DecisionTableResponse> getDecisionTablesForProcessDefinition(
            @ApiParam(name = "processDefinitionId") @PathVariable String processDefinitionId,
            HttpServletRequest request) {

        List<DmnDecisionTable> decisionTables = repositoryService.getDecisionTablesForProcessDefinition(processDefinitionId);

        return restResponseFactory.createDecisionTableResponseList(decisionTables, processDefinitionId);
    }
}
