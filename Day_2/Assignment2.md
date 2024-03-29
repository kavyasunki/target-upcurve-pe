```sql
--1. Write a query to display the lastname, department number, and department name for all employees.
select E.LAST_NAME, E.DEPARTMENT_ID, D.DEPARTMENT_NAME FROM EMPLOYEES E JOIN DEPARTMENTS D ON E.DEPARTMENT_ID=D.DEPARTMENT_ID;

--2. Create a unique listing of all jobs that are in department 30. Include the location id in the output.
SELECT DISTINCT j.JOB_ID, d.LOCATION_ID
FROM jobs j
JOIN job_history jh ON j.JOB_ID = jh.JOB_ID
JOIN departments d ON jh.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE d.DEPARTMENT_ID = 30;

--3. Write a query to display the employee lastname, department name, location id, and city of all employees who earn a commission.
SELECT e.LASTNAME, d.DEPARTMENT_NAME, l.LOCATION_ID, l.CITY
FROM employees e
JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
JOIN locations l ON d.LOCATION_ID = l.LOCATION_ID
WHERE e.COMMISSION_PCT IS NOT NULL;

--4. Display the employee lastname, and department name for all employees who have an "a" in their lastname.
SELECT e.LASTNAME, d.DEPARTMENT_NAME
FROM employees e
JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE e.LASTNAME LIKE '%a%';

--5. Write a query to display the lastname, job, department number, and department name for all employees who work in Toronto.
SELECT e.LASTNAME, j.JOB_TITLE, d.DEPARTMENT_ID, d.DEPARTMENT_NAME
FROM employees e
JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
JOIN locations l ON d.LOCATION_ID = l.LOCATION_ID
JOIN jobs j ON e.JOB_ID = j.JOB_ID
WHERE l.CITY = 'Toronto';

--6. Display the employee lastname and employee number along with their manager's lastname and manager number. Label the columns "Employee", "Emp#", "Manager" and "Manager#" respectively.
SELECT e1.LASTNAME AS Employee, e1.EMPLOYEE_ID AS "Emp#", e2.LASTNAME AS Manager, e2.EMPLOYEE_ID AS "Manager#"
FROM employees e1
JOIN employees e2 ON e1.MANAGER_ID = e2.EMPLOYEE_ID;

--7. Modify the above to display the same columns for all employees (including "King", who has no manager). Order the result by the employee number.
SELECT e1.LASTNAME AS Employee, e1.EMPLOYEE_ID AS "Emp#", e2.LASTNAME AS Manager, e2.EMPLOYEE_ID AS "Manager#"
FROM employees e1
LEFT JOIN employees e2 ON e1.MANAGER_ID = e2.EMPLOYEE_ID
ORDER BY e1.EMPLOYEE_ID;

--8. Create query that displays employee lastnames, department numbers, and all the employees who work in the same department as a given employee. Give each column an appropriate label.
SELECT 
    e1.LASTNAME AS Employee_Lastname,
    e1.DEPARTMENT_ID AS Department_Number,
    e2.LASTNAME AS Same_Department_Employee_Lastname
FROM 
    employees e1
JOIN 
    employees e2 ON e1.DEPARTMENT_ID = e2.DEPARTMENT_ID
WHERE 
    e1.LASTNAME = 'Kumar'; 

--9. Create a query that displays the name, job, department name, salary and grade for all employees.
select emp.last_name, emp.job_id, dept.department_id, emp.salary, jb.grade_level
from EMPLOYEES as emp
join DEPARTMENTS as dept
on emp.department_id = dept.department_id
join JOB_GRADES jb
on emp.salary between jb.lowest_sal and jb.highest_sal;

--10. Create a query to display the name and hiredate of any employee hired after employee "Davies"
SELECT 
    FIRSTNAME, LASTNAME, HIRE_DATE 
FROM employees 
WHERE 
    HIRE_DATE > (SELECT HIRE_DATE FROM employees WHERE LASTNAME = 'Davies');

--11. Display the names and hire dates for all employees who were hired before their managers, along with their manager's names and hiredates. Label the columns "Employee", "Emp hired", "Manager", and "Manager hired" respectively.

SELECT 
    e1.FIRSTNAME AS Employee,
    e1.HIRE_DATE AS "Emp hired",
    e2.FIRSTNAME AS Manager,
    e2.HIRE_DATE AS "Manager hired"
FROM 
    employees e1
JOIN 
    employees e2 ON e1.MANAGER_ID = e2.EMPLOYEE_ID
WHERE 
    e1.HIRE_DATE < e2.HIRE_DATE;

--12. Display the highest, lowest, sum and average salary of all employees. Label the columns "Maximum", "Minimum", "Sum", and "Average" respectively.
SELECT 
    MAX(SALARY) AS Maximum,
    MIN(SALARY) AS Minimum,
    SUM(SALARY) AS Sum,
    AVG(SALARY) AS Average
FROM 
    employees;


--13. Modify the above query to display the same data for each job type.
SELECT 
    JOB_ID,
    MAX(SALARY) AS Maximum,
    MIN(SALARY) AS Minimum,
    SUM(SALARY) AS Sum,
    AVG(SALARY) AS Average
FROM 
    employees
GROUP BY 
    JOB_ID;

--14. Write a query to display the number of people with the same job.
SELECT 
    JOB_ID,
    COUNT(*) AS Number_of_People
FROM 
    employees
GROUP BY 
    JOB_ID;

--15. Determine the number of managers without listing them. Label the column "Number of Managers". [Hint: use the MANAGER_ID column to determine the number of managers]
SELECT 
    COUNT(DISTINCT MANAGER_ID) AS "Number of Managers"
FROM 
    employees;

--16. Write a query that displays the difference between the highest and the lowes salaries. Label the column as "Difference".
SELECT 
    (SELECT MAX(SALARY) FROM employees) - (SELECT MIN(SALARY) FROM employees) AS Difference;

--17. Display the manager number and the salary of the lowest paid employee for that manager. Exclude anyone whose manager is not known. Exclude any group where the minimum salary is less than $6000. Sort the output in descending order of salary.
SELECT 
    e2.MANAGER_ID AS Manager_Number,
    MIN(e1.SALARY) AS Lowest_Salary
FROM 
    employees e1
JOIN 
    employees e2 ON e1.MANAGER_ID = e2.EMPLOYEE_ID
WHERE 
    e2.MANAGER_ID IS NOT NULL
GROUP BY 
    e2.MANAGER_ID
HAVING 
    MIN(e1.SALARY) >= 6000
ORDER BY 
    Lowest_Salary DESC;

--18. Write a query to display each department's name, location, number of employees, and the average salary for all employees in that department. Label the columns "Name", "Location", "No.of people", and "SAlary" respectively. Round the average salary to two decimal places.
SELECT 
    d.DEPARTMENT_NAME AS Name,
    l.CITY AS Location,
    COUNT(e.EMPLOYEE_ID) AS "No.of people",
    AVG(e.SALARY) AS Salary
FROM 
    departments d
JOIN 
    employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
JOIN 
    locations l ON d.LOCATION_ID = l.LOCATION_ID
GROUP BY 
    d.DEPARTMENT_NAME, l.CITY;

--19. Write a query to display the lastname, and hiredate of any employee in the department as the employee "Zlotkey". Exclude "Zlotkey".
SELECT 
    LASTNAME, 
    HIRE_DATE
FROM 
    employees
WHERE 
    DEPARTMENT_ID = (SELECT DEPARTMENT_ID FROM employees WHERE LASTNAME = 'Zlotkey')
    AND LASTNAME != 'Zlotkey';

--20. Create a query to display the employee numbers and lastnames of all employees who earn more than the average salary. Sort the result in ascending order of salary.
SELECT 
    EMPLOYEE_ID,
    LASTNAME
FROM 
    employees
WHERE 
    SALARY > (SELECT AVG(SALARY) FROM employees)
ORDER BY 
    SALARY ASC;
--21. Write a query that displays the employee number and lastname of all employees who work in a department with any employee whose lastname contains a "u".
SELECT 
    EMPLOYEE_ID, 
    LASTNAME 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID IN (SELECT DISTINCT DEPARTMENT_ID FROM employees WHERE LASTNAME LIKE '%u%');

--22. Display the lastname, department number, and job id of all employees whose department location id is 1700.
SELECT 
    LASTNAME, 
    DEPARTMENT_ID, 
    JOB_ID 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID IN (SELECT DEPARTMENT_ID FROM departments WHERE LOCATION_ID = 1700);

--23. Display the lastname and salary of every employee who reports to "King".
SELECT 
    LASTNAME, 
    SALARY 
FROM 
    employees 
WHERE 
    MANAGER_ID = (SELECT EMPLOYEE_ID FROM employees WHERE LASTNAME = 'King');

--24. Display the department number, lastname, and job id for every employee in the "Executive" department.
SELECT 
    DEPARTMENT_ID, 
    LASTNAME, 
    JOB_ID 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID = (SELECT DEPARTMENT_ID FROM departments WHERE DEPARTMENT_NAME = 'Executive');
--25. Display the employee number, lastname, and salary of all employees who earn more than the average salary and who work in a department with any employee with a "u" in their lastname.
SELECT 
    EMPLOYEE_ID, 
    LASTNAME, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT AVG(SALARY) FROM employees) 
    AND DEPARTMENT_ID IN (SELECT DISTINCT DEPARTMENT_ID FROM employees WHERE LASTNAME LIKE '%u%');


--26. Write a query to get unique department ID from employee table.
SELECT DISTINCT 
    DEPARTMENT_ID 
FROM 
    employees;

--27. Write a query to get all employee details from the employee table order by first name, descending.
SELECT 
    * 
FROM 
    employees 
ORDER BY 
    FIRSTNAME DESC;


--28. Write a query to get the names (first_name, last_name), salary, PF of all the employees (PF is calculated as 15% of salary).
SELECT 
    FIRSTNAME, 
    LASTNAME, 
    SALARY, 
    SALARY * 0.15 AS PF 
FROM 
    employees;

--29. Write a query to get the employee ID, names (first_name, last_name), salary in ascending order of salary.
SELECT 
    EMPLOYEE_ID, 
    FIRSTNAME, 
    LASTNAME, 
    SALARY 
FROM 
    employees 
ORDER BY 
    SALARY ASC;

--30. Write a query to get the total salaries payable to employees.
SELECT 
    SUM(SALARY) AS Total_Salaries_Payable 
FROM 
    employees;

--31. Write a query to get the maximum and minimum salary from employees table.
SELECT 
    MAX(SALARY) AS Maximum_Salary, 
    MIN(SALARY) AS Minimum_Salary 
FROM 
    employees;

--32. Write a query to get the average salary and number of employees in the employees table.
SELECT 
    AVG(SALARY) AS Average_Salary, 
    COUNT(*) AS Number_of_Employees 
FROM 
    employees;

--33. Write a query to get the number of employees working with the company.
SELECT 
    COUNT(*) AS Number_of_Employees 
FROM 
    employees;

--34. Write a query to get the number of jobs available in the employees table.
SELECT 
    COUNT(DISTINCT JOB_ID) AS Number_of_Jobs 
FROM 
    employees;

--35. Write a query get all first name from employees table in upper case.
SELECT 
    UPPER(FIRST_NAME) AS Uppercase_First_Name 
FROM 
    employees;

--36. Write a query to get the first 3 characters of first name from employees table.
SELECT 
    LEFT(FIRST_NAME, 3) AS First_Three_Characters 
FROM 
    employees;

--37. Write a query to get the names (for example Ellen Abel, Sundar Ande etc.) of all the employees from employees table.
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name 
FROM 
    employees;

--38. Write a query to get first name from employees table after removing white spaces from both side.
SELECT 
    TRIM(FIRST_NAME) AS Trimmed_First_Name 
FROM 
    employees;

--39. Write a query to get the length of the employee names (first_name, last_name) from employees table.
SELECT 
    TRIM(FIRST_NAME) AS Trimmed_First_Name 
FROM 
    employees;

--40. Write a query to check if the first_name fields of the employees table contains numbers.
SELECT 
    FIRST_NAME 
FROM 
    employees 
WHERE 
    FIRST_NAME REGEXP '[0-9]';

--41. Write a query to select first 10 records from a table.
SELECT 
    * 
FROM 
    EMPLOYEES 
LIMIT 10;

--42. Write a query to get monthly salary (round 2 decimal places) of each and every employee.
SELECT 
    ROUND(SALARY / 12, 2) AS Monthly_Salary 
FROM 
    employees;

--43. Write a query to display the name (first_name, last_name) and salary for all employees whose salary is not in the range $10,000 through $15,000.
SELECT 
    ROUND(SALARY / 12, 2) AS Monthly_Salary 
FROM 
    employees;

--44. Write a query to display the name (first_name, last_name) and department ID of all employees in departments 30 or 100 in ascending order.
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    DEPARTMENT_ID 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID IN (30, 100) 
ORDER BY 
    DEPARTMENT_ID ASC;

--45. Write a query to display the name (first_name, last_name) and salary for all employees whose salary is not in the range $10,000 through $15,000 and are in department 30 or 100.
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY NOT BETWEEN 10000 AND 15000 
    AND DEPARTMENT_ID IN (30, 100);

--46. Write a query to display the name (first_name, last_name) and hire date for all employees who were hired in 1987.
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    HIRE_DATE 
FROM 
    employees 
WHERE 
    YEAR(HIRE_DATE) = 1987;

--47. Write a query to display the first_name of all employees who have both "b" and "c" in their first name.
SELECT 
    FIRST_NAME 
FROM 
    employees 
WHERE 
    FIRST_NAME LIKE '%b%' 
    AND FIRST_NAME LIKE '%c%';

--48. Write a query to display the last name, job, and salary for all employees whose job is that of a Programmer or a Shipping Clerk, and whose salary is not equal to $4,500, $10,000, or $15,000.
SELECT 
    FIRST_NAME 
FROM 
    employees 
WHERE 
    FIRST_NAME LIKE '%b%' 
    AND FIRST_NAME LIKE '%c%';

--49. Write a query to display the last name of employees whose names have exactly 6 characters.
SELECT 
    LAST_NAME 
FROM 
    employees 
WHERE 
    LENGTH(LAST_NAME) = 6;

--50. Write a query to display the last name of employees having 'e' as the third character.
SELECT 
    LAST_NAME 
FROM 
    employees 
WHERE 
    SUBSTRING(LAST_NAME, 3, 1) = 'e';

--51. Write a query to display the jobs/designations available in the employees table.
SELECT 
    DISTINCT JOB_ID 
FROM 
    employees;

--52. Write a query to display the name (first_name, last_name), salary and PF (15% of salary) of all employees.
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY, 
    SALARY * 0.15 AS PF 
FROM 
    employees;

--53. Write a query to select all record from employees where last name in 'BLAKE', 'SCOTT', 'KING' and 'FORD'.
SELECT 
    * 
FROM 
    employees 
WHERE 
    LAST_NAME IN ('BLAKE', 'SCOTT', 'KING', 'FORD');

--54. Write a query to list the number of jobs available in the employees table.
SELECT 
    COUNT(DISTINCT JOB_ID) AS Number_of_Jobs 
FROM 
    employees;

--55. Write a query to get the total salaries payable to employees.
SELECT 
    COUNT(DISTINCT JOB_ID) AS Number_of_Jobs 
FROM 
    employees;

--56. Write a query to get the minimum salary from employees table.
SELECT 
    MIN(SALARY) AS Minimum_Salary 
FROM 
    employees;

--57. Write a query to get the maximum salary of an employee working as a Programmer.
SELECT 
    MAX(SALARY) AS Maximum_Salary 
FROM 
    employees 
WHERE 
    JOB_ID = 'Programmer';

--58. Write a query to get the average salary and number of employees working the department 90.
SELECT 
    AVG(SALARY) AS Average_Salary, 
    COUNT(*) AS Number_of_Employees 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID = 90;

--59. Write a query to get the highest, lowest, sum, and average salary of all employees.
SELECT 
    MAX(SALARY) AS Highest_Salary, 
    MIN(SALARY) AS Lowest_Salary, 
    SUM(SALARY) AS Total_Salary, 
    AVG(SALARY) AS Average_Salary 
FROM 
    employees;

--60. Write a query to get the number of employees with the same job.
SELECT 
    MAX(SALARY) AS Highest_Salary, 
    MIN(SALARY) AS Lowest_Salary, 
    SUM(SALARY) AS Total_Salary, 
    AVG(SALARY) AS Average_Salary 
FROM 
    employees;

--61. Difference between Highest and Lowest Salaries
SELECT 
    (MAX(SALARY) - MIN(SALARY)) AS Salary_Difference 
FROM 
    employees;

--62. Manager ID and Salary of Lowest-Paid Employee for Each Manager
SELECT 
    MANAGER_ID, 
    MIN(SALARY) AS Lowest_Salary 
FROM 
    employees 
WHERE 
    MANAGER_ID IS NOT NULL 
GROUP BY 
    MANAGER_ID;

--63. Department ID and Total Salary Payable in Each Department
SELECT 
    DEPARTMENT_ID, 
    SUM(SALARY) AS Total_Salary_Payable 
FROM 
    employees 
GROUP BY 
    DEPARTMENT_ID;

--64. Average Salary for Each Job ID Excluding Programmer
SELECT 
    JOB_ID, 
    AVG(SALARY) AS Average_Salary 
FROM 
    employees 
WHERE 
    JOB_ID <> 'Programmer' 
GROUP BY 
    JOB_ID;

--65. Total Salary, Maximum, Minimum, and Average Salary for Employees in Department 90
SELECT 
    SUM(SALARY) AS Total_Salary, 
    MAX(SALARY) AS Maximum_Salary, 
    MIN(SALARY) AS Minimum_Salary, 
    AVG(SALARY) AS Average_Salary 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID = 90;

--66. Job ID and Maximum Salary for Employees with Maximum Salary >= $4000
SELECT 
    JOB_ID, 
    MAX(SALARY) AS Maximum_Salary 
FROM 
    employees 
WHERE 
    SALARY >= 4000 
GROUP BY 
    JOB_ID;

--67. Average Salary for Departments Employing More Than 10 Employees
SELECT 
    DEPARTMENT_ID, 
    AVG(SALARY) AS Average_Salary 
FROM 
    employees 
GROUP BY 
    DEPARTMENT_ID 
HAVING 
    COUNT(*) > 10;

--68. Employees with Higher Salary Than Employee 'Bull'
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT SALARY FROM employees WHERE LAST_NAME = 'Bull');

--69. Employees Working in the IT Department
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name 
FROM 
    employees 
WHERE 
    DEPARTMENT_ID = (SELECT DEPARTMENT_ID FROM departments WHERE DEPARTMENT_NAME = 'IT');

--70. Employees Who Have a Manager and Work in a USA Based Department
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name 
FROM 
    employees 
WHERE 
    MANAGER_ID IS NOT NULL 
    AND DEPARTMENT_ID IN (SELECT DEPARTMENT_ID FROM departments WHERE COUNTRY_ID = 'US');

--71. Employees Who Are Managers
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name 
FROM 
    employees 
WHERE 
    EMPLOYEE_ID IN (SELECT DISTINCT MANAGER_ID FROM employees);

--72. Employees with Salary Higher Than Average Salary
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT AVG(SALARY) FROM employees);

--73. Employees Whose Salary Equals Minimum Salary for Their Job Grade
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    (JOB_ID, SALARY) IN (SELECT JOB_ID, MIN(SALARY) FROM employees GROUP BY JOB_ID);

--74. Employees Earning More Than Average Salary and Working in IT Departments
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT AVG(SALARY) FROM employees) 
    AND DEPARTMENT_ID IN (SELECT DEPARTMENT_ID FROM departments WHERE DEPARTMENT_NAME LIKE '%IT%');

--75. Employees Earning More Than Mr. Bell
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT SALARY FROM employees WHERE LAST_NAME = 'Bell');

--76. Employees Earning Same Salary as Minimum Salary in All Departments
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY = (SELECT MIN(SALARY) FROM employees);

--77. Employees Earning More Than Average Salary of All Departments
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT AVG(SALARY) FROM employees);

--78. Employees Earning Higher Salary Than Shipping Clerks
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > ALL (SELECT SALARY FROM employees WHERE JOB_ID = 'SH_CLERK') 
    AND JOB_ID <> 'SH_CLERK' 
ORDER BY 
    SALARY ASC;

--79. Employees Who Are Not Supervisors
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Employee_Name 
FROM 
    employees 
WHERE 
    EMPLOYEE_ID NOT IN (SELECT DISTINCT MANAGER_ID FROM employees);

--80. Employee Details
SELECT 
    EMPLOYEE_ID, 
    FIRST_NAME, 
    LAST_NAME, 
    department_name 
FROM 
    employees 
JOIN 
    departments 
ON 
    employees.department_id = departments.department_id;

--81. Employees with Salary Above Average for Their Departments
SELECT 
    EMPLOYEE_ID, 
    FIRST_NAME, 
    LAST_NAME, 
    SALARY 
FROM 
    employees 
WHERE 
    SALARY > (SELECT AVG(SALARY) FROM employees WHERE department_id = employees.department_id);

--82. Even Numbered Records
SELECT 
    * 
FROM 
    (SELECT *, ROW_NUMBER() OVER() AS row_num FROM employees) AS emp 
WHERE 
    MOD(row_num, 2) = 0;

--83. 5th Maximum Salary
SELECT 
    DISTINCT SALARY 
FROM 
    employees 
ORDER BY 
    SALARY DESC 
LIMIT 
    4, 1;

--84. Leading Zeros Before Maximum and Minimum Salaries
--85. Last 10 Records
SELECT 
    * 
FROM 
    (SELECT *, ROW_NUMBER() OVER(ORDER BY some_column DESC) AS row_num FROM your_table) AS emp 
WHERE 
    row_num <= 10;

--86. Departments with No Employees
SELECT 
    DEPARTMENT_ID, 
    DEPARTMENT_NAME 
FROM 
    departments 
WHERE 
    DEPARTMENT_ID NOT IN (SELECT DISTINCT DEPARTMENT_ID FROM employees);

--87. 3 Maximum Salaries
SELECT 
    DISTINCT SALARY 
FROM 
    employees 
ORDER BY 
    SALARY DESC 
LIMIT 
    3;

--88. 3 Minimum Salaries
SELECT 
    DISTINCT SALARY 
FROM 
    employees 
ORDER BY 
    SALARY ASC 
LIMIT 
    3;

--89. Nth Max Salaries
SELECT 
    DISTINCT SALARY 
FROM 
    employees 
ORDER BY 
    SALARY DESC 
LIMIT 
    n-1, 1;

--90. Department Locations
SELECT 
    L.LOCATION_ID, 
    L.STREET_ADDRESS, 
    L.CITY, 
    L.STATE_PROVINCE, 
    L.COUNTRY_NAME 
FROM 
    departments D 
JOIN 
    locations L 
ON 
    D.LOCATION_ID = L.LOCATION_ID;

--91. Employee Details
SELECT 
    E.FIRST_NAME, 
    E.LAST_NAME, 
    D.DEPARTMENT_ID, 
    D.DEPARTMENT_NAME 
FROM 
    employees E 
JOIN 
    departments D 
ON 
    E.DEPARTMENT_ID = D.DEPARTMENT_ID;

--92. Employees Working in London
SELECT 
    E.LAST_NAME, 
    E.DEPARTMENT_ID, 
    E.JOB_ID 
FROM 
    employees E 
JOIN 
    departments D 
ON 
    E.DEPARTMENT_ID = D.DEPARTMENT_ID 
WHERE 
    D.CITY = 'London';

--93. Employee and Manager Details
SELECT 
    E.FIRST_NAME || ' ' || E.LAST_NAME AS Employee_Name, 
    E.SALARY, 
    M.FIRST_NAME || ' ' || M.LAST_NAME AS Manager_Name 
FROM 
    employees E 
JOIN 
    employees M 
ON 
    E.MANAGER_ID = M.EMPLOYEE_ID;

--94. Employees Hired After 'Jones'
SELECT 
    FIRST_NAME, 
    LAST_NAME, 
    HIRE_DATE 
FROM 
    employees 
WHERE 
    HIRE_DATE > (SELECT HIRE_DATE FROM employees WHERE LAST_NAME = 'Jones');

--95. Department Names and Number of Employees
SELECT 
    D.DEPARTMENT_NAME, 
    COUNT(*) AS Number_of_Employees 
FROM 
    employees E 
JOIN 
    departments D 
ON 
    E.DEPARTMENT_ID = D.DEPARTMENT_ID 
GROUP BY 
    D.DEPARTMENT_NAME;

--96. Job Details in Department 90
SELECT 
    JOB_ID, 
    DATEDIFF(END_DATE, START_DATE) AS Days_Worked 
FROM 
    job_history 
WHERE 
    DEPARTMENT_ID = 90;

--97. Department and Manager Details
SELECT 
    D.DEPARTMENT_ID, 
    D.DEPARTMENT_NAME, 
    M.FIRST_NAME || ' ' || M.LAST_NAME AS Manager_Name 
FROM 
    departments D 
JOIN 
    employees M 
ON 
    D.MANAGER_ID = M.EMPLOYEE_ID;

--98. Department, Manager, and City Details
SELECT 
    D.DEPARTMENT_NAME, 
    CONCAT(E.FIRST_NAME, ' ', E.LAST_NAME) AS Manager_Name, 
    L.CITY 
FROM 
    departments D 
JOIN 
    employees E 
ON 
    D.MANAGER_ID = E.EMPLOYEE_ID 
JOIN 
    locations L 
ON 
    D.LOCATION_ID = L.LOCATION_ID;

--99. Job Titles and Average Salaries
SELECT 
    JOB_ID, 
    AVG(SALARY) AS Average_Salary 
FROM 
    employees 
GROUP BY 
    JOB_ID;

--100. Job Title, Employee Name, and Salary Difference from Minimum
SELECT 
    E.JOB_ID, 
    CONCAT(E.FIRST_NAME, ' ', E.LAST_NAME) AS Employee_Name, 
    E.SALARY - J.MIN_SALARY AS Salary_Difference 
FROM 
    employees E 
JOIN 
    jobs J 
ON 
    E.JOB_ID = J.JOB_ID;

--101. Job History for Employees Earning More Than $10,000
SELECT 
    * 
FROM 
    job_history 
WHERE 
    EMPLOYEE_ID IN (SELECT EMPLOYEE_ID FROM employees WHERE SALARY > 10000);

--102. Employees with Manager Experience More Than 15 Years
SELECT 
    E.FIRST_NAME || ' ' || E.LAST_NAME AS Employee_Name, 
    E.SALARY, 
    ROUND((SYSDATE - E.HIRE_DATE)/365) AS Years_Experience 
FROM 
    employees E 
JOIN 
    employees M 
ON 
    E.MANAGER_ID = M.EMPLOYEE_ID 
GROUP BY 
    E.FIRST_NAME, 
    E.LAST_NAME, 
    E.SALARY, 
    E.HIRE_DATE 
HAVING 
    (SYSDATE - M.HIRE_DATE)/365 > 15;

--103. Employees Hired Between June 1, 1987, and July 30, 1987
SELECT 
    FIRST_NAME, 
    HIRE_DATE 
FROM 
    employees 
WHERE 
    HIRE_DATE BETWEEN TO_DATE('1987-06-01', 'YYYY-MM-DD') AND TO_DATE('1987-07-30', 'YYYY-MM-DD');

--104. Employees Joined in June
SELECT 
    FIRST_NAME, 
    LAST_NAME 
FROM 
    employees 
WHERE 
    EXTRACT(MONTH FROM HIRE_DATE) = 6;

--105. Years in Which More Than 10 Employees Joined
SELECT 
    EXTRACT(YEAR FROM HIRE_DATE) AS Join_Year, 
    COUNT(*) AS Number_of_Employees 
FROM 
    employees 
GROUP BY 
    EXTRACT(YEAR FROM HIRE_DATE) 
HAVING 
    COUNT(*) > 10;

--106. Employees Who Joined in 1987
SELECT 
    FIRST_NAME 
FROM 
    employees 
WHERE 
    EXTRACT(YEAR FROM HIRE_DATE) = 1987;

--107. Managers with More Than 5 Years Experience
SELECT 
    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS Manager_Name, 
    SALARY, 
    ROUND((SYSDATE - HIRE_DATE)/365) AS Years_Experience 
FROM 
    employees 
WHERE 
    EMPLOYEE_ID IN (SELECT MANAGER_ID FROM employees) 
HAVING 
    ROUND((SYSDATE - HIRE_DATE)/365) > 5;

--108. Employee ID, Last Name, and First Salary Date
SELECT 
    EMPLOYEE_ID, 
    LAST_NAME, 
    (SELECT MIN(START_DATE) FROM job_history WHERE EMPLOYEE_ID = employees.EMPLOYEE_ID) AS First_Salary_Date 
FROM 
    employees;

--109. Employee First Name, Hire Date, and Experience
SELECT 
    FIRST_NAME, 
    HIRE_DATE, 
    ROUND((SYSDATE - HIRE_DATE)/365) AS Experience 
FROM 
    employees;

--110. Department ID, Year, and Number of Employees Joined
SELECT 
    DEPARTMENT_ID, 
    EXTRACT(YEAR FROM HIRE_DATE) AS Join_Year, 
    COUNT(*) AS Number_of_Employees 
FROM 
    employees 
GROUP BY 
    DEPARTMENT_ID, 
    EXTRACT(YEAR FROM HIRE_DATE);

--111. Job ID and Related Employee ID
SELECT 
    JOB_ID, 
    EMPLOYEE_ID 
FROM 
    employees;

--112. Update Phone Numbers
UPDATE 
    employees 
SET 
    PHONE_NUMBER = REPLACE(PHONE_NUMBER, '124', '999');

--113. Employees with First Names Longer Than 8 Characters
SELECT 
    * 
FROM 
    employees 
WHERE 
    LENGTH(FIRST_NAME) >= 8;

--114. Leading Zeros Before Maximum and Minimum Salaries
--115. Append '@example.com' to Email Field
UPDATE 
    employees 
SET 
    EMAIL = CONCAT(EMAIL, '@example.com');

--116. Employee ID, First Name, Last Name, and Hire Month
SELECT 
    EMPLOYEE_ID, 
    FIRST_NAME, 
    LAST_NAME, 
    EXTRACT(MONTH FROM HIRE_DATE) AS Hire_Month 
FROM 
    employees;

--117. Employee ID and Email ID (Without Last Three Characters)
SELECT 
    EMPLOYEE_ID, 
    SUBSTR(EMAIL, 1, LENGTH(EMAIL) - 3) AS Email_ID 
FROM 
    employees;

--118. Employees with First Names in Upper Case
SELECT 
    * 
FROM 
    employees 
WHERE 
    FIRST_NAME = UPPER(FIRST_NAME);

--119. Last 4 Characters of Phone Numbers
SELECT 
    SUBSTR(PHONE_NUMBER, -4) AS Last_Four_Characters 
FROM 
    employees;

--120. Last Word of Street Address
SELECT 
    SUBSTRING_INDEX(STREET_ADDRESS, ' ', -1) AS Last_Word 
FROM 
    locations;

--121. Locations with Minimum Street Length
SELECT 
    LOCATION_ID, 
    STREET_ADDRESS 
FROM 
    locations 
WHERE 
    LENGTH(STREET_ADDRESS) = (SELECT MINLENGTH(LENGTH(STREET_ADDRESS)) FROM locations);

--122. First Word from Job Titles with More Than One Word
SELECT 
    SUBSTRING_INDEX(JOB_TITLE, ' ', 1) AS First_Word 
FROM 
    jobs 
WHERE 
    INSTR(JOB_TITLE, ' ') > 0;

--123. Length of First Names for Employees with 'c' After 2nd Position in Last Name
SELECT 
    LENGTH(FIRST_NAME) AS First_Name_Length 
FROM 
    employees 
WHERE 
    LAST_NAME LIKE '__c%';

--124. First Name and Length for Employees with Names Starting with 'A', 'J', or 'M'
SELECT 
    FIRST_NAME, 
    LENGTH(FIRST_NAME) AS First_Name_Length 
FROM 
    employees 
WHERE 
    FIRST_NAME LIKE 'A%' OR FIRST_NAME LIKE 'J%' OR FIRST_NAME LIKE 'M%' 
ORDER BY 
    FIRST_NAME;

--125. First Name and Salary with Salary Padded to 10 Characters
--126. First Eight Characters of First Names and Salaries with Thousands Sign
--127. Employees Hired on 7th Day of Any Month or 7th Month in Any Year
SELECT 
    EMPLOYEE_ID, 
    FIRST_NAME, 
    LAST_NAME, 
    HIRE_DATE 
FROM 
    employees 
WHERE 
    EXTRACT(DAY FROM HIRE_DATE) = 7 OR EXTRACT(MONTH FROM HIRE_DATE) = 7;
```