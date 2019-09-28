import TestCommunicate from '../views/TestCommunicate.vue'

let routers = [
    {
    path: "/",
    redirect: "/test"
    },
    {
        path:"/test",
        component:TestCommunicate,
        name:"test"
    },
];
export default routers