import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Index from '../views/Index.vue'
import DeptartmentList from '../views/system/DeptartmentList.vue'
import UserList from '../views/system/UserList.vue'
import Hello from '../components/Hello.vue'
import UpdateAccount from '../views/UpdateAccount.vue'
import Register from '../views/Register.vue'
import ForgetPassword from '../views/ForgetPassword.vue'
import NewDepartment from '../views/system/NewDepartment.vue'
import UpdateDepartment from '../views/system/UpdateDepartment.vue'
import DrugAdministration from '../views/system/DrugAdministration.vue'
import NewDrug from '../views/system/NewDrug.vue' 
import PatientRegister from '../views/system/PatientRegister.vue'
import MedicalRecord from '../views/system/MedicalRecord.vue'
import DoctorDiagnosis from '../views/system/DoctorDiagnosis.vue'
import ToDoctorDiagnosis from '../views/system/ToDoctorDiagnosis.vue'
import ToMedicalRecord from '../views/system/ToMedicalRecord.vue'
import DocView from '../views/system/DocView.vue'
import ToDocView from '../views/system/ToDocView.vue'
import Salary from '../views/system/Salary.vue'
import InWard from '../views/system/InWard.vue'
import Ward from '../views/system/Ward.vue'
import ToInWard from '../views/system/ToInWard.vue'
import BulletinBoard from "../views/system/BulletinBoard.vue";
import WeatherForecast from "@/views/system/WeatherForecast.vue";
import RechargeAccount from "@/views/system/RechargeAccount.vue";
import NewBulletin from "@/views/system/NewBulletin.vue";


Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'Home',
    component: Login
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/index',
    name: 'Index',
    component: Index,
    children:[
      {
        path: '/index',
        name: 'Hello',
        component: Hello
      },
      {
        path: '/index/deptartmentList',
        name: 'DeptartmentList',
        component: DeptartmentList
      },
      {
        path: '/index/userList',
        name: 'UserList',
        component: UserList
      },{
        path: '/index/newDepartment',
        name: 'NewDepartment',
        component: NewDepartment
      },{
        path: '/index/updateDepartment',
        name: 'UpdateDepartment',
        component: UpdateDepartment
      },{
        path: '/index/drugAdministration',
        name: 'DrugAdministration',
        component: DrugAdministration
      },{
        path: '/index/newDrug',
        name: 'NewDrug',
        component:NewDrug
      },{
        path: '/index/patientRegister',
        name: 'PatientRegister',
        component:PatientRegister
      },{
        path: '/index/medicalRecord',
        name: 'MedicalRecord',
        component:MedicalRecord
      },{
        path: '/index/doctorDiagnosis',
        name: 'DoctorDiagnosis',
        component:DoctorDiagnosis
      },{
        path: '/index/toDoctorDiagnosis',
        name: 'ToDoctorDiagnosis',
        component:ToDoctorDiagnosis
      },{
        path: '/index/toMedicalRecord',
        name: 'ToMedicalRecord',
        component:ToMedicalRecord
      },{
        path: '/index/docView',
        name: 'DocView',
        component:DocView
      },{
        path: '/index/toDocView',
        name: 'ToDocView',
        component:ToDocView
      },{
        path: '/index/salary',
        name: 'Salary',
        component:Salary
      },{
        path: '/index/inWard',
        name: 'InWard',
        component:InWard
      },{
        path: '/index/ward',
        name: 'Ward',
        component:Ward
      },{
        path: '/index/toInWard',
        name: 'ToInWard',
        component:ToInWard
      },
      {
        path: '/index/bulletinBoard',
        name: 'BulletinBoard',
        component:BulletinBoard
      },
      {
        path: '/index/weatherForecast',
        name: 'WeatherForecast',
        component:WeatherForecast
      },
      {
        path: '/index/rechargeAccount',
        name: 'RechargeAccount',
        component: RechargeAccount
      },
      {
        path: '/index/newBulletin',
        name: 'NewBulletin',
        component: NewBulletin
      },
    ]
  },
  {
    path: '/updateAccount',
    name: 'UpdateAccount',
    component: UpdateAccount
  },{
    path: '/register',
    name: 'Register',
    component: Register
  },{
    path: '/forgetPassword',
    name: 'ForgetPassword',
    component: ForgetPassword
  },

]

const router = new VueRouter({
  routes
})

export default router
