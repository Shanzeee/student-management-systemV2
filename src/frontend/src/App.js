import logo from './logo.svg';
import './App.css';
import {Header} from "./components/nav-bar";
import {BrowserRouter, Route, Routes, useRoutes} from "react-router-dom";
import {NotFoundPage} from "../../../../usos/src/frontend/src/pages/not-found";
import {UnauthorizedPage} from "./pages/unauthorized";
import {HomePage} from "../../../../usos/src/frontend/src/pages/home";
import {RegisterPage} from "./pages/Register";
import {DepartmentPage} from "../../../../usos/src/frontend/src/pages/department";
import {CoursePage} from "../../../../usos/src/frontend/src/pages/course";
import {TeacherPage} from "../../../../usos/src/frontend/src/pages/teacher";
import {GroupPage} from "../../../../usos/src/frontend/src/pages/group";
import {GradePage} from "../../../../usos/src/frontend/src/pages/grade";
import {StudentPage} from "../../../../usos/src/frontend/src/pages/student";
import {ExamPage} from "./pages/examin";
import LoginForm from "./pages/Login";

function App() {
  return (
      <BrowserRouter>
        <Header/>
        <div className="container">
          <Routes>
            <Route path="/" element={<HomePage/>} />
            <Route path="/home"  element={<HomePage/>} />
            <Route path="/login"  element={<LoginForm/>} />
            <Route path="/register"  element={<RegisterPage/>} />
            <Route path="/departments"  element={<DepartmentPage/>} />
            <Route path="/courses"  element={<CoursePage/>} />
            <Route path="/teachers"  element={<TeacherPage/>} />
              <Route path="/students"  element={<StudentPage/>} />
            <Route path="/exams"  element={<ExamPage />}/>
            <Route path="/groups"  element={<GroupPage/>} />
            <Route path="/grades"  element={<GradePage/>} />
            <Route path="/404"  element={<NotFoundPage/>} />
            <Route path="/401"  element={<UnauthorizedPage/>} />
            <Route path="*" element={<NotFoundPage/>} />


            {/*<Route path="/profile"  element={*/}
            {/*  <AuthGuard roles={[Role.ADMIN, Role.USER]}>*/}
            {/*    <ProfilePage/>*/}
            {/*  </AuthGuard>*/}

            {/*}/>*/}


            {/*<Route path="/admin"  element={*/}
            {/*  <AuthGuard roles={Role.ADMIN}>*/}
            {/*    <AdminPage/>*/}
            {/*  </AuthGuard>*/}

            {/*}/>*/}


          </Routes>
        </div>
      </BrowserRouter>

  );
}

export default App;
