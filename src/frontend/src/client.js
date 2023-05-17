const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    // convert non-2xx HTTP responses into errors:
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}
// STUDENTS

export const getAllStudents = () =>
    fetch("api/v1/students")
        .then(checkStatus);

export const createNewStudent = student =>
    fetch("api/v1/students", {
            headers: {
                'Content-Type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(student)
        }
    ).then(checkStatus)

// TEACHERS
export const getAllTeachers = () =>
    fetch("api/v1/teachers")
        .then(checkStatus);

export const createNewTeacher = teacher =>
    fetch("api/v1/teachers", {
            headers: {
                'Content-Type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(teacher)
        }
    ).then(checkStatus)

// DEPARTMENTS
export const getAllDepartments = () =>
    fetch("api/v1/departments")
        .then(checkStatus);

export const createNewDepartment = department =>
    fetch("api/v1/departments", {
            headers: {
                'Content-Type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(department)
        }
    ).then(checkStatus)

// GROUP

export const getAllGroups = () =>
    fetch("api/v1/groups")
        .then(checkStatus);

export const createNewGroup = group =>
    fetch("api/v1/groups", {
            headers: {
                'Content-Type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(group)
        }
    ).then(checkStatus)


// COURSE

export const getAllCourses = () =>
    fetch("api/v1/courses")
        .then(checkStatus);

export const createNewCourse = course =>
    fetch("api/v1/courses", {
            headers: {
                'Content-Type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(course)
        }
    ).then(checkStatus)


// EXAM

export const getAllExams = () =>
    fetch("api/v1/exams")
        .then(checkStatus);

export const addNewExam = exam =>
    fetch("api/v1/exams", {
            headers: {
                'Content-Type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(exam)
        }
    ).then(checkStatus)



