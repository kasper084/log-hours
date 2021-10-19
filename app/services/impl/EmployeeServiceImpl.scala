package services.impl

import daos.EmployeeDAO
import services.EmployeeService

import javax.inject.Inject

class EmployeeServiceImpl @Inject()(employeeDAO: EmployeeDAO) extends EmployeeService {


}
