// import React, { Component } from 'react';
// import {connect} from "react-redux";
// import {getAllMovies} from  '../../actions/satishActions';
// // import { Header, Grid, Card, Label, Menu, Input, Dropdown } from "semantic-ui-react";
// import {bindActionCreators} from 'redux';
// import '../../assets/css/userProfile.css';
// import '../../assets/css/movies.css';
// import {SearchBox, SearchKitComponent, SearchkitManager, SearchkitProvider, Hits, NoHits, InitialLoader, HitItemProps} from "searchkit";

// // const searchKit = new SearchkitManager("http://localhost:8080/movies/all");

// // export const searchBar = () => (
// //   <SearchkitProvider searchkit={searchKit}>
// //     <div>
// //       <Hits hitsPerPage={10} sourceFilter={["title","directorName", "actors", "genre"]}/>
// //       <NoHits translations={{
// //         "NoHits.NoResultsFound":"No movies found were found for {query}",
// //         "NoHits.DidYouMean":"Search for {suggestion}",
// //         "NoHits.SearchWithoutFilters":"Search for {query} without filters"
// //       }} suggestionsField="title"/>
// //     </div>
// //   </SearchkitProvider>
// // );
// const InitialLoaderComponent = (props) =>(
//   <div className={props.bemBlocks.item().mix(props.bemBlocks.container("title"))}></div>
// )
// class SearchMovie extends SearchKitComponent{
//   render(){
//     return(
//       <SearchkitProvider searchkit={searchKit}>
//         <div>
//           <Hits hitsPerPage={10} sourceFilter={["title","directorName", "actors", "genre"]}/>
//           <NoHits translations={{
//             "NoHits.NoResultsFound":"No movies found were found for {query}",
//             "NoHits.DidYouMean":"Search for {suggestion}",
//             "NoHits.SearchWithoutFilters":"Search for {query} without filters"
//           }} suggestionsField="title"/>
//         </div>
//       </SearchkitProvider>
//     )
//   }
// }

// export default SearchMovie;
