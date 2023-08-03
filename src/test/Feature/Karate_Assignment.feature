Feature: User validate pet creation E2E

  # Read data from Excel
  Background:
    * def java_file = Java.type('Karate_Assignments.Reusable')
    * def result = java_file.read_Properties_File('createpet_api')

  # PET ID creation
  Scenario Outline: user validate POST call pet id
    * def txt_Pet_ID = java_file.read_Excel("<TC_ID>",'petid')
    * def txt_Pet_name = java_file.read_Excel("<TC_ID>",'petname')
    * def body = java_file.createPet_Req_Body(txt_Pet_ID,txt_Pet_name)
    Given url result
    And header Content-type = 'application/json; charset=utf-8'
    When request body
    When method POST
    Then status 200
    And match responseType =='json'
    And print response
    And assert response.id == txt_Pet_ID
    And assert response.name == txt_Pet_name
    And assert response.status == 'Active'
    # GET PET ID
    * def Pet_ID_ = java_file.float_to_int(txt_Pet_ID)
    Given url 'https://petstore.swagger.io/v2/pet/'+Pet_ID_
    When method GET
    Then status 200
    And match responseType =='json'
    And print response
    And assert response.id == txt_Pet_ID
    And assert response.name == txt_Pet_name
    And assert response.status == 'Active'
    # Delete PET ID
    Given url 'https://petstore.swagger.io/v2/pet/'+Pet_ID_
    When method DELETE
    Then status 200
    And match responseType =='json'
    And print response
    And assert response.code== 200
    And assert response.message == Pet_ID_

    Examples:
      |TC_ID|
      |TC_001|
      |TC_002|
      |TC_003|
      |TC_004|
      |TC_005|
      |TC_006|
      |TC_007|
      |TC_008|
      |TC_009|
      |TC_010|
      |TC_011|
      |TC_012|
      |TC_013|
      |TC_014|
      |TC_015|
      |TC_016|
      |TC_017|
      |TC_018|
      |TC_019|
      |TC_020|
      |TC_021|
      |TC_022|
      |TC_023|
      |TC_024|
      |TC_025|
      |TC_026|
      |TC_027|
      |TC_028|
      |TC_029|
      |TC_030|
