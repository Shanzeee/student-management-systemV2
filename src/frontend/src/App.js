import logo from './logo.svg';
import './App.css';
import {Header} from "./components/nav-bar";
import {BrowserRouter, Route, Routes, useRoutes} from "react-router-dom";
import {NotFoundPage} from "./pages/not-found";
import {UnauthorizedPage} from "./pages/unauthorized";
import {HomePage} from "./pages/home";
import {LoginPage} from "./pages/Login";
import {RegisterPage} from "./pages/Register";
import {DepartmentPage} from "./pages/department";
import {CoursePage} from "./pages/course";
import {TeacherPage} from "./pages/teacher";
import {GroupPage} from "./pages/group";
import {GradePage} from "./pages/grade";
import {StudentPage} from "./pages/student";
import {ExamPage} from "./pages/examin";

function App() {
  return (
      <BrowserRouter>
        <Header/>
        <div className="container">
          <Routes>
            <Route path="/" element={<HomePage/>} />
            <Route path="/home"  element={<HomePage/>} />
            <Route path="/login"  element={<LoginPage/>} />
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
