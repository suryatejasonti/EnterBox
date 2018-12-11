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
// import SearchMovie from './MoviesSearch';


class BrowseMovies extends Component{

  constructor(props){
    super(props);
  }

  componentWillMount(){
    this.props.getAllMovies();
  }

  renderList(movies) {
    return(
      <Grid className="movies-list" stackable>
        <Row className = "show-grid">
          { movies.map((movie, idx) => {
          return(
          <Col xs={3}  md={3} textAlign="center" key={idx}>
            <Link className="movie-card-link" to={`/movies/:${movie.movieId}`}>
              <div className="movie-display-card">
                <img className="movie-card-image" src = {`${movie.image}`} alt ="image" />
                <p>Price:{movie.price}</p>
              </div>
            </Link>
          </Col>
          )
        })}
        </Row>
      </Grid>
    )
  }

  onSubmit(values) {
    console.log(values.user);
    if (this.props.user === 'user') {
      this.props.searchUsers(values.user);
    }
    else {
      this.props.searchMoviehallUsers(values.user);
    }
  }

  render() {
    const{moviesList,handleSubmit} = this.props;
    console.log(moviesList);
    return (
      <div className="browse-movies-div">
        {/* <SearchMovie/> */}
        <div>
        <Carousel>
        <Carousel.Item>
          <img className="carasoul-image" height={450} alt="900x500" src="https://hdeuropix.io/images/sliders/fantastic-beasts-the-crimes-of-grindelwal-2018.jpg" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="carasoul-image"  height={450} alt="900x500" src="https://hdeuropix.io/images/sliders/venom-2018.jpg" />
        </Carousel.Item>
        <Carousel.Item>
          <img  className="carasoul-image" height={450} alt="900x500" src="https://hdeuropix.io/images/sliders/night-school-2018.jpg" />
          {/*<Carousel.Caption>*/}
            {/*<h3>Third slide label</h3>*/}
            {/*<p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>*/}
          {/*</Carousel.Caption>*/}
        </Carousel.Item>
      </Carousel>;
      </div>
        {  (moviesList && moviesList.length > 0) ? this.renderList(moviesList) : ''}
      </div>
    )
  }
}

function mapStateToProps(state) {
  return {
    moviesList : state.moviesList.movies
  }
}
export  default connect(mapStateToProps,{getAllMovies}) (BrowseMovies);
