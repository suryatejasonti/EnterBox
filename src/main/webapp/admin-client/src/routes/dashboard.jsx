import Dashboard from "views/Dashboard/Dashboard";
import UserProfile from "views/UserProfile/UserProfile";
import Searchmovie from "views/UserProfile/Searchmovie";
import TableList from "views/TableList/TableList";
import Typography from "views/Typography/Typography";
import Icons from "views/Icons/Icons";
import Maps from "views/Maps/Maps";
import Notifications from "views/Notifications/Notifications";
import Upgrade from "views/Upgrade/Upgrade";
import AddMovieHalls from '../admin/addMovies';
import MovieEdit from '../admin/adminMovieSearch/movieEdit';
import BrowseMovies from "../components/AdminMovieEdit/BrowseMovies"
import FindUsers from "../components/Adminedit/FindUsers";
import AdminUserEdit from "../components/Adminedit/AdminUserEdit";
import FindMoviehallUsers from "../components/AdminMoviehallUserEdit/FindMoviehallUsers";
import AdminMoviehallUserEdit from "../components/AdminMoviehallUserEdit/AdminMovehallUserEdit"
import SearchBill from '../admin/searchBill';
import SearchBillMonth from '../admin/searchBillMonth';
import EditMovieHalls from '../admin/editMovieHallAdmin';

const dashboardRoutes = [

    {
        path: "/dashboard",
        name: "Dashboard",
        icon: "pe-7s-graph",
        component: Dashboard
    },
    {
      path: "/browseMovies",
      name: "Movies",
      icon: "pe-7s-note2",
      component: BrowseMovies
     },
    {
        path: "/findUsers",
        component: FindUsers,
        name: "Find Users",
        icon: "pe-7s-note2",
    },
    {
        path: "/findSubscribedUsers",
        component: FindMoviehallUsers,
        name: "Find Subscribed Users",
        icon: "pe-7s-note2",
    },
    {
        path: "/table",
        name: "Revenue",
        icon: "pe-7s-note2",
        component: TableList
    },
    {
        path: "/searchmovie",
        name: "Search/Edit Movie",
        icon: "pe-7s-note2",
        component: Searchmovie
    },
    {
        path: "/Add Movie",
        name: "Add Movies",
        icon: "pe-7s-note2",
        component: AddMovieHalls
    },
    {
        path: "/search-bills-month",
        name: "Search Bills Month",
        icon: "pe-7s-note2",
        component: SearchBillMonth
    },
    {
        path: "/search-bills",
        component: SearchBill,
        icon: "pe-7s-note2",
        name: "Search Bills Date",
    },
    {
        path: "/edit-movie",
        component: EditMovieHalls,
        icon: "pe-7s-note2",
        name: "Edit Movie",
    },
    // {
    //     path: "/AdminUserEdit",
    //     component: AdminUserEdit
    // },
    // {
    //     path: "/AdminMoviehallUseredit",
    //     component: AdminMoviehallUserEdit
    // },
    // {
    //     path: "/admin-movie-edit/:tmdbid",
    //     component: MovieEdit,

    // },


    /*{
      path: "/typography",
      name: "Typography",
      icon: "pe-7s-news-paper",
      component: Typography
    },
    { path: "/icons", name: "Icons", icon: "pe-7s-science", component: Icons },
    { path: "/maps", name: "Maps", icon: "pe-7s-map-marker", component: Maps },
    {
      path: "/notifications",
      name: "Notifications",
      icon: "pe-7s-bell",
      component: Notifications
    },*/
    /*{
      upgrade: true,
      path: "/upgrade",
      name: "Upgrade to PRO",
      icon: "pe-7s-rocket",
      component: Upgrade
    },*/
    // { redirect: true, path: "/", to: "/dashboard", name: "Dashboard" }
];

export default dashboardRoutes;
