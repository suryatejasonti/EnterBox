import React, { Component } from 'react';
import {connect} from "react-redux";
import {getAllMovies} from  '../../actions/satishActions';
import {Carousel,Grid,Row,Col} from 'react-bootstrap';
import FindUsers from '../Adminedit/FindUsers'
import {Link} from 'react-router-dom';
// import { Header, Grid, Card, Label, Menu, Input, Dropdown } from "semantic-ui-react";
import {Field, reduxForm, initialize, formValueSelector} from 'redux-form';
import {bindActionCreators} from 'redux';
import '../../assets/css/userProfile.css'
import '../../assets/css/movies.css'

class movieDetailspage extends Component{
  constructor(props){
    super(props);
  }

  componentWillMount(){
    this.props.getmovieId();
  }

  render(){
    return(
      <div>

      </div>
    );
  }


}

function mapStateToProps(state) {
  return {
    moviesList : state.moviesList.movies
  }
}
export default connect(mapStateToProps,{getAllMovies}) (BrowseMovies);
