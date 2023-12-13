package User;

import FC.FC;

import java.util.ArrayList;
import java.util.UUID;

public class Employee extends User {
    private int salary;

    public Employee() {
        this(0);
    }

    public Employee(int salary) {
        String uniqueID = UUID.randomUUID().toString();
        super.setID(uniqueID);
        this.salary = salary;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) throws Exception {
        if (salary < 0 || salary > 1000000) throw new Exception("Invalid salary");
        this.salary = salary;
    }

    @Override
    public boolean create() throws Exception {
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Employee");
        myController.appendFile(data);
        return true;
    }

    @Override
    public Object read(String id) throws Exception {
        ArrayList<Employee> employees = this.getAll();
        Employee myEmployee = null;

        for (Employee employee : employees) {
            if (id.equals(employee.getID())) {
                myEmployee = employee;
                break;
            }
        }

        return myEmployee;
    }


    @Override
    public boolean update(Object obj) throws Exception {
        if (!(obj instanceof Employee oldEmp)) {
            throw new Exception("Invalid Employee");
        }
        ArrayList<String> myOldData = this.toArrayList(oldEmp);
        String oldData = FC.encode(myOldData);
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Employee");
        myController.updateFile(oldData, data);
        return true;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        if (!(obj instanceof Employee deleteEmp)) {
            throw new Exception("Invalid Employee");
        }
        ArrayList<String> myData = this.toArrayList(deleteEmp);
        String data = FC.encode(myData);
        FC myController = new FC("Employee");
        myController.updateFile(data, "");
        return true;
    }

    @Override
    public ArrayList<Employee> getAll() throws Exception {
        ArrayList<Employee> myData = new ArrayList<>();
        FC myController = new FC("Employee");
        ArrayList<String> employees = myController.readFile();
        for (String employee : employees) {
            Employee myEmployee = new Employee();
            ArrayList<String> employeeData = FC.decode(employee);
            myEmployee.setID(employeeData.get(0));
            myEmployee.setName(employeeData.get(1));
            myEmployee.setAge(Integer.parseInt(employeeData.get(2)));
            myEmployee.setSalary(Integer.parseInt(employeeData.get(3)));
            myData.add(myEmployee);
        }
        return myData;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        if (!(obj instanceof Employee myEmployee)) {
            throw new Exception("Invalid Employee");
        }
        ArrayList<String> employee = new ArrayList<>();
        employee.add(myEmployee.getID());
        employee.add(myEmployee.getName());
        employee.add(Integer.toString(myEmployee.getAge()));
        employee.add(Integer.toString(myEmployee.getSalary()));
        return employee;
    }
}
