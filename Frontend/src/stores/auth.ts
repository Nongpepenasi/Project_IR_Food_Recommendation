import { defineStore } from 'pinia'

import axios from 'axios'

import type { AxiosInstance } from 'axios'
// import type { EventOrganizer } from '@/type'
import type { StudentItem, TeacherItem } from '@/type'

const apiClient: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_URL,

  withCredentials: false,

  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: null as string | null,
    userRole: null as string[] | null,
    id: null as string | null
  }),
  getters: {
    currentUserName(): string {
        return ''
    },
    isAdmin(): boolean {
        return this.userRole?.includes('ROLE_ADMIN') || false
    }
  },
  actions: {
    login(email: string, password: string) {
      return apiClient
        .post('/api/v1/auth/authenticate', {
          username: email,
          password: password
        })
        .then((response) => {
            this.token = response.data.access_token
            // this.user = response.data.user
            this.userRole = response.data.user_role
            this.id = response.data.id
            localStorage.setItem('access_token', this.token as string)
            localStorage.setItem('user_role', JSON.stringify(this.userRole))
            localStorage.setItem('id', this.id as string)
            console.log(response.data.id)
            return response
        })
        
    },
    async studentRegister(
      username: string, 
      firstName: string, 
      lastName: string, 
      email: string, 
      password: string,
      images: string[]
      ) {
      const response = await apiClient.post('/api/v1/auth/register/student', {
        username: username,
        firstname: firstName,
        lastname: lastName,
        email: email,
        password: password,
        images: images
      })
    
      return response
    },
    async teacherRegister(
      username: string, 
      firstName: string, 
      lastName: string, 
      email: string, 
      password: string,
      images: string[],
      department: string,
      academic: string
      ) {
      const response = await apiClient.post('/api/v1/auth/register/teacher', {
        username: username,
        firstname: firstName,
        lastname: lastName,
        email: email,
        password: password,
        images: images,
        department: department,
        academic: academic
      })
   
      return response
    },
    async register(
      username: string, 
      firstName: string, 
      lastName: string, 
      email: string, 
      password: string,
      ) {
      const response = await apiClient.post('/api/v1/auth/register', {
        username: username,
        firstname: firstName,
        lastname: lastName,
        email: email,
        password: password,
      })
   
      return response
    },
    async studentUpdateProfile(id: string, firstName: string, lastName: string,images:string[]) {
      const response = await apiClient.put('/updatestudents', {
        id: id,
        name: firstName,
        surname: lastName,
        images: images
      })
      return response
    },
    async teacherUpdateProfile(id: string, firstName: string, lastName: string,images:string[]) {
      const response = await apiClient.put('/updateteachers', {
        id: id,
        name: firstName,
        surname: lastName,
        images: images
      })
      return response
    },
    async setRelation(id: string, firstName: string) {
      const response = await apiClient.put('/setRelation', {
        id: id,
        name: firstName,
      })
      return response
    },
    async announcementPost(title: string, description: string, files: string[]) {
      const response = await apiClient.post('/announcement', {
        title: title,
        description: description,
        files: files
      })
      return response
    },
    logout() {
        console.log('logout')
        this.token = null
        this.userRole = null
        this.id = null
        localStorage.removeItem('access_token')
        localStorage.removeItem('user_role')
        localStorage.removeItem('id')
    },
    reload(token: string, userRole: string[], id: string) {
        this.token = token
        this.userRole = userRole
        this.id = id
    }
  }
})
